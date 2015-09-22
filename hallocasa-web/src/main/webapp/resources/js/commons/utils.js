/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Change or update a query string
 * @param {type} uri
 * @param {type} key
 * @param {type} value
 * @returns {String}
 */
function updateQueryStringParameter(uri, key, value) {
    var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
    var separator = uri.indexOf('?') !== -1 ? "&" : "?";
    if (uri.match(re)) {
        return uri.replace(re, '$1' + key + "=" + value + '$2');
    }
    else {
        return uri + separator + key + "=" + value;
    }
}

/**
 * Scroll pages until an object is visible
 * @returns {undefined}
 */
function scrollToObject(  $object ) {
//    var container = $('body > div > div');
//     
//    alert(container.offset().top);
//    alert($object.offset().top);
//    
//   
//    container.scrollTop(
//        $object.offset().top - container.offset().top + container.scrollTop()
//    );
//    
}