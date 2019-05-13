sap.ui.define([
	"sap/ui/core/mvc/Controller"
], function (Controller) {
	"use strict";

	function addResultToModel(result, model) {
		var resultPrev = model.getProperty("/result");
		if (resultPrev) {
			model.setProperty("/result",
				result.concat(resultPrev));
		} else {
			model.setProperty("/result", result);
		}
	}

	return Controller.extend("text.TextServices.controller.Language", {

		API_KEY: "", // API Key einfügen

		// TODO onInit
		onInit: function () {
			this.getView().setModel(new sap.ui.model.json.JSONModel(), "items");
		},

		// TODO onPressLanguage
		onPressLanguage: function (event) {

			var view = this.getView();
			var model = view.getModel("items");
			var text = view.byId("idtext").getValue();

			// TODO Code Snippet (JavaScript)

			var data = JSON.stringify({
				"message": text // text verwenden
			});

			var xhr = new XMLHttpRequest();
			xhr.withCredentials = false;

			xhr.addEventListener("readystatechange", function () {
				if (this.readyState === this.DONE) {
					/* eslint-disable no-console */
					console.log(this.responseText);

					// TODO Ergebnis im Modell speichern
					var response = JSON.parse(this.responseText);

					var detections = response.detections;
					var percent = Math.round(detections[0].confidence * 100);
					var result = [{
						Text: text,
						Result: detections[0].langStr + " (" + percent + "%)"
					}];
					addResultToModel(result, model);

					// TODO [WORKFLOW] Source und Target Language
					var sourceLang = detections[0].langCode;
					var targetLang = (sourceLang === "en") ? "de" : "en";
					model.setProperty("/sourceLanguage", sourceLang);
					model.setProperty("/targetLanguage", targetLang);
					var buttonTranslate = view.byId("buttonTranslate");
					buttonTranslate.setText(
						"Translate (" + sourceLang + "->" + targetLang + ")");
				}
			});

			var sandbox = true;
			if (sandbox) {
				xhr.open("POST", "/ml/languagedetection/lang-detect/");
			} else {
				xhr.open("POST", "/mlftrial-language-detection");
			}

			//adding request headers
			xhr.setRequestHeader("content-type", "application/json");
			xhr.setRequestHeader("accept", "application/json");
			//API Key for API Sandbox
			xhr.setRequestHeader("apikey", this.API_KEY);

			//sending request
			xhr.send(data);
		},

		onPressTranslate: function (event) {

			var view = this.getView();
			var model = view.getModel("items");
			var text = view.byId("idtext").getValue();

			// TODO Code Snippet (SAPUI5)

			var sourceLang = "en";
			var targetLang = "de";
			// TODO sourceLanguage, targetLanguage

			var sourceLanguage = model.getProperty("/sourceLanguage");
			var targetLanguage = model.getProperty("/targetLanguage");
			if (sourceLanguage && targetLanguage) {
				sourceLang = sourceLanguage;
				targetLang = targetLanguage;
			}

			//Create JSON Model with URL
			var oModel = new sap.ui.model.json.JSONModel();

			//API Key for API Sandbox
			var sHeaders = {
				"Content-Type": "application/json",
				"APIKey": this.API_KEY
			};

			var oData = JSON.stringify({
				"sourceLanguage": sourceLang,
				"targetLanguages": [
					targetLang
				],
				"units": [{
					"value": text,
					"key": "CART_CONTENTS"
				}]
			});

			// TODO Busy-Anzeige öffnen
			var oBusyIndicator = new sap.m.BusyDialog();
			oBusyIndicator.open();

			oModel.loadData("/ml/translation/translation", oData, true, "POST", null, false, sHeaders);

			oModel.attachRequestCompleted(function (oEvent) {
				var oData = oEvent.getSource().getData();
				/* eslint-disable no-console */
				console.log(oData);

				// TODO Busy-Anzeige schließen
				oBusyIndicator.close();

				// TODO error
				var error = oEvent.getParameter("errorobject");
				if (error) {
					var errorResult = [{
						Text: text,
						Result: JSON.stringify(error)
					}];
					addResultToModel(errorResult, model);
					return;
				}

				// TODO Übersetzung im Modell speichern
				var translation = oData.units[0].translations[0].value;
				var result = [{
					Text: text,
					Result: translation
				}];
				addResultToModel(result, model);

				// TODO [WORKFLOW] productText setzen
				var productText = (sourceLang === "en") ? text : translation;
				model.setProperty("/productText", productText);
			});
		},

		onPressProductText: function (event) {

			var view = this.getView();
			var model = view.getModel("items");
			var text = view.byId("idtext").getValue();

			// TODO [WORKFLOW] productText lesen
			var productText = model.getProperty("/productText");
			if (productText) {
				text = productText;
			}

			// TODO Product Text Code Snippet (JavaScript)
			var data = new FormData();
			/* eslint-disable sap-no-dom-insertion */
			data.append("texts", text);

			var xhr = new XMLHttpRequest();
			xhr.withCredentials = false;

			xhr.addEventListener("readystatechange", function () {
				if (this.readyState === this.DONE) {
					/* eslint-disable no-console */
					console.log(this.responseText);

					// TODO Product Text Classification im Modell speichern
					var response = JSON.parse(this.responseText);
					var categories = [];
					response.predictions[0].results.forEach(function (result) {
						if (result.category) {
							var percent = Math.round(result.confidence * 100);
							if (percent > 0) {
								categories.push(
									result.category + " (" + percent + "%)");
							}
						} else {
							var percentS = Math.round(result.score * 100);
							if (percentS > 0) {
								categories.push(
									result.label + " (" + percentS + "%)");
							}
						}
					});

					var result = [{
						Text: text,
						Result: categories.join(", ")
					}];
					addResultToModel(result, model);
				}
			});

			var sandbox = false;
			if (sandbox) {
				xhr.open("POST", "/ml/producttextclassifier/inference_sync");
				xhr.setRequestHeader("APIKey", this.API_KEY);
			} else {
				xhr.open("POST", "/mlftrial-text-classifier/");
			}

			//sending request
			xhr.send(data);
		}

	});
});