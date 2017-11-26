var R;
function setRAndDraw(r){
    reDraw();
    drawArea(r);
    R = r;
    document.getElementById("MainForm:HiddenR2").value = r;
    drawFromTable(r);
}

function validation(x ,y){
    if(
        x>5 || x<-5 || isNaN(x) || x == null
    ) return false;

    if( y<-5 || y>3 || isNaN(y) || y == null
    ) return false;

     if ( R < 1 || R > 5 || R == null) return false;
    return true;
}