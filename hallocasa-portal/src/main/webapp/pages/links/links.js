/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function adjustImageSize($image) {
    var w = $image.width();
    var h = $image.height();
    var pw = $image.parent().width();
    var ph = $image.parent().height();
    
    if ( (w / h) > (pw / ph)) {
        $image.attr("width", "100%");
    } else {
        $image.attr("height", "100%");
    }
}
