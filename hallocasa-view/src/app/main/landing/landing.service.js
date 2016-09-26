(function() {
  'use strict';

  angular
    .module('HalloCasa')
    .service('LandingService', LandingService);

  function LandingService() {
    var service = {
      getProfiles: getProfiles
    };

    return service;

    function getProfiles(){
      return [
        {
          name: "Otoniel Guerrero",
          city: "Cabrera",
          languages: ["English", "Español"],
          profession: ["Traductor"," Experto"],
          avatarURL: "user28.jpg"
        },
        {
          name: "Yedys Pana Lubo",
          city: "Bogotá, D.C.",
          languages: [
            "Corredor",
            "Tasador",
            "Administrador",
            "Experto"
          ],
          profession: ["Español"],
          avatarURL: "user17.jpg"
        },
        {
          name: "German Antonio Urueta Rivero",
          city: "Bogotá, D.C.",
          languages: [
            "Corredor",
            "Tasador",
            "Administrador",
            "Experto"
          ],
          profession: ["Español"],
          avatarURL: "user16.jpg"
        },
        {
          name: "Ed Schuh",
          city: "Bogotá, D.C.",
          languages: [
            "English",
            "Español",
            "Deutsch"
          ],
          profession: [
            "Experto"
          ],
          avatarURL: "user9.jpg"
        },
        {
          name: "Favhyan Horta",
          city: "Bogotá, D.C.",
          languages: ["Español"],
          profession: ["Administrador"],
          avatarURL: "user6.jpg"
        },
        {
          name: "Grupo FANAVI. Compañía Inmobiliaria",
          city: "Bogotá, D.C.",
          languages: [
            "Corredor",
            "Tasador",
            "Administrador"
          ],
          profession: ["Español"],
          avatarURL: "user870.jpg"
        },
        {
          name: "Tu Habitat Colombia Inmobiliaria",
          city: "Bogotá, D.C.",
          languages: ["Español"],
          profession: ["Corredor"],
          avatarURL: "user42.jpg"
        },
        {
          name: "Maria Clara Rivera Restrepo",
          city: "Cali",
          languages: ["Español"],
          profession: [
            "Corredor",
            "Experto"
          ],
          avatarURL: "user45.jpg"
        },
        {
          name: "Consuelo Fernandez",
          city: "Bogotá, D.C.",
          languages: ["Español"],
          profession: ["Corredor"],
          avatarURL: "user885.jpg"
        },
        {
          name: "Catalina Martinez",
          city: "Bogotá, D.C.",
          languages: [
            "English",
            "Español"
          ],
          profession: [
            "Traductor",
            "Experto"
          ],
          avatarURL: "user5.jpg"
        }
      ];
    }
  }
})();
