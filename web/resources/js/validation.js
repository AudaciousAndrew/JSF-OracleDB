function setRAndDraw(r){
    reDraw();
    drawArea(r);
    document.getElementById("MainForm:hiddenR").value = r;
    alert(document.getElementById("MainForm:hiddenR").value);
}