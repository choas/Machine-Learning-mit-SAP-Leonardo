sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/ui/demokit/TagCloud",
	"sap/ui/demokit/Tag"
], function (Controller, TagCloud, Tag) {
	"use strict";

	/* eslint-disable no-console */
	/* eslint-disable sap-no-dom-insertion */
	/* eslint-disable no-warning-comments */

	// TODO function showTagCloud
	function showTagCloud(view, response) {
		var model = view.getModel("config");
		var mappingSongToGenre = model
			.getProperty("/mappingSongToGenre");
		response.predictions
			.forEach(function (prediction, index) {
				var playlist = view.byId("playlist" + index);
				var grid = view.byId("grid" + index);
				// TODO Genre-Scores
				var genreScores = {};
				prediction.keywords.forEach(function (keyword, k) {
					var score = prediction.scores[k];
					var genreScore = Math.ceil(score * 1000);
					var song = keyword[0];
					var genre = mappingSongToGenre[song];
					if (genreScores.hasOwnProperty(genre)) {
						genreScores[genre] += genreScore;
					} else {
						genreScores[genre] = genreScore;
					}
				});

				// TODO TagCloud
				var tagCloud = grid.getContent()[2];
				if (!tagCloud) {
					tagCloud = new sap.ui.demokit.TagCloud({
						minFontSize: 12,
						maxFontSize: 56,
						press: function (event) {
							var tagId = event.getParameter("tagId");
							var genre = sap.ui.getCore().byId(tagId).getText();
							var mappingGenreToSong =
								model.getProperty("/mappingGenreToSong");
							var song = mappingGenreToSong[genre];
							playlist.setValue(
								playlist.getValue() + " " + song);
						}
					}).addStyleClass("blTagCloud");
					grid.addContent(tagCloud);
				}

				// TODO Tags
				tagCloud.destroyTags();
				var isChecked = view.byId("check").getSelected();
				var genres = model.getProperty("/genres");
				genres.forEach(function (genre) {
					var scores = genreScores[genre];
						var tag = new sap.ui.demokit.Tag();
					if (scores > 0 || isChecked) {
						tag.setText(genre);
					}
						tag.setWeight(scores);
						tag.setTooltip("" + (scores / 1000));
						tagCloud.addTag(tag);
				});

			});
	}

	// TODO function topicDetection
	function topicDetection(view, topics) {
		var busyDialog = new sap.m.BusyDialog();
		busyDialog.open();
		var model = view.getModel("config");
		var data = new FormData();
		var users = model.getProperty("/users");
		users.forEach(function (user, index) {
			var playlist = view.byId("playlist" + index)
				.getValue();
			var blob = new Blob([playlist], {
				type: "text/plain"
			});
			data.append("files", blob, user.name + ".txt");
		});
		var options = {
			"numTopics": topics,
			"numTopicsPerDoc": 5,
			"numKeywordsPerTopic": 5
		};
		data.append("options", JSON.stringify(options));
		var xhr = new XMLHttpRequest();
		xhr.withCredentials = false;
		xhr.addEventListener("readystatechange", function () {
			if (this.readyState === this.DONE) {
				var response = JSON.parse(this.responseText);
				console.log(response);
				busyDialog.close();
				showTagCloud(view, response);
			}
		});
		xhr.open("POST", "/ml/topicdetection/topic-detection");
		xhr.setRequestHeader("APIKey", ); // API KEY einfuegen
		xhr.send(data);
	}

	return Controller.extend(
		"playlist.Playlist.controller.Main", {
			onInit: function () {
				// TODO onInit
				var view = this.getView();
				var model = this.getOwnerComponent().getModel("config");
				model.attachRequestCompleted(function (event) {
					view.setModel(model, "config");
					var users = model.getProperty("/users");
					users.forEach(function (user, index) {
						view.byId("name" + index).setText(user.name);
						view.byId("playlist" + index).setValue(user.playlist);
					});
					topicDetection(view, 4);
				});

				// Abweichung zum Buch
				// In einer neueren SAPUI5 Version wird das Modell 
				// bereits geladen und die Daten stehen zur Verfügung.
				// In diesem Fall wird der obere Code in der
				// attachRequestCompleted nicht ausgeführt, da kein
				// entsprechendes Event ausgelöst wird.
				// Um dies zu erreichen wird die fireRequestCompleted
				// Methode aufgerufen, die das 'requestCompleted'
				// Event auslöst.
				if (model.getProperty("/users").length > 0) {
					model.fireRequestCompleted();
				}
			},
			refreshTopics: function (event) {
				// TODO refreshTopic
				var view = this.getView();
				var selectedItem = view.byId("topicNum").getSelectedItem();
				var topics = selectedItem.getKey().split("-")[1];
				topicDetection(view, topics);

			}
		});
});