/* global QUnit*/

sap.ui.define([
	"sap/ui/test/Opa5",
	"text/TextServices/test/integration/pages/Common",
	"sap/ui/test/opaQunit",
	"text/TextServices/test/integration/pages/Language",
	"text/TextServices/test/integration/navigationJourney"
], function (Opa5, Common) {
	"use strict";
	Opa5.extendConfig({
		arrangements: new Common(),
		viewNamespace: "text.TextServices.view.",
		autoWait: true
	});
});