var R;
function setRAndDraw(r){
    reDraw();
    drawArea(r);
    R = r;
    document.getElementById("MainForm:hiddenR").value = r;
   // alert(document.getElementById("MainForm:hiddenR").value);
    alert("R= "+R);
}