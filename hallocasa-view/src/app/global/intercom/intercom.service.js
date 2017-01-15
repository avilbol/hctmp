(function() {
  'use strict';

  angular
    .module('HalloCasa.global')
    .service('IntercomService', IntercomService);

  /** @ngInject */
  function IntercomService($intercom, $log) {
    var service = {
      surveyCallback: surveyCallback,
      declineCallback: declineCallback,
      responseCallback: responseCallback
    };

    var testDate = new Date();

    return service;

    function surveyCallback() {
      $log.debug("wootric_survey_callback");
      $intercom.update({"last_wootric_surveyed_at": Math.round((testDate.getTime())/1000) });
    }

    function declineCallback() {
      $log.debug("wootric_decline_callback");
      $intercom.update({"last_wootric_declined_at":  Math.round((testDate.getTime())/1000)});
    }

    function responseCallback(score, comment) {
      $log.debug("wootric_response_callback");
      $intercom.update({"last_wootric_score": parseInt(score),"last_wootric_comment": decodeURIComponent(comment)});
    }
  }
})();
