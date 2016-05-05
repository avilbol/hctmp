/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function() {
    
    var adjustBackground = function() {
        $window = $( window );
        $background = $( '.land-page-background' );
        if ( $background.height() > 638 ){
            $background.css( 'background-size', 'auto 100%');
        }else{
            $background.css( 'background-size', 'auto 638px');
        }
    };
    $(window).resize( adjustBackground );
    adjustBackground();
});
