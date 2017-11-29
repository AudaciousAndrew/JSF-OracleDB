var canvas = document.querySelector('canvas');
var c = canvas.getContext('2d');
fitToContainer(canvas);
drawAxes(c);
fillAxes();


function fitToContainer(canvas){
    // Make it visually fill the positioned parent
    canvas.style.width ='100%';
    canvas.style.height='100%';
    // ...then set the internal size to match
    canvas.width  = canvas.offsetWidth;
    canvas.height = canvas.offsetHeight;
}

function drawAxes (c) {
    //Axes
    c.beginPath();
    c.fillStyle = "#000";
    c.font = "15px Verdana";
    c.moveTo((canvas.width / 2) + 0.5, canvas.height + 0.5);
    c.lineTo((canvas.width / 2) + 0.5, 0.5);
    c.fillText("Y", (canvas.width / 2) + 5, 15);
    c.moveTo(canvas.width, (canvas.height / 2));
    c.lineTo(0, canvas.height / 2);
    c.fillText("X", canvas.width - 15, (canvas.height / 2) - 5);
    c.strokeStyle = '#000';
    c.stroke();
    c.closePath();
}
function fillAxes() {
    c.beginPath();
    // fill Y loop
    for( i = 1 ; i<12 ; i++ ){
        if( i != 6) {
            c.moveTo((canvas.width / 2) + 5, i * canvas.height / 12);
            c.lineTo((canvas.width / 2) - 5, i * canvas.height / 12);
            c.fillText(6 - i, (canvas.width / 2) + 12, i * canvas.height / 12 + 5);
        }
    }
    // fill X loop
    for( i = 1 ; i <12 ; i++){
        if ( i != 6) {
            c.moveTo(i * canvas.width / 12, (canvas.height / 2) - 5);
            c.lineTo(i * canvas.width / 12, (canvas.height / 2) + 5);
            c.fillText( i-6, i * canvas.width / 12 - 5, (canvas.height / 2) - 15);
        }
    }
    c.stroke();
    c.closePath();
}

function drawArea(r) {
    c.beginPath();
    // rect
    c.fillStyle='rgba(30, 144, 255 , 0.5)';
    c.rect( ((canvas.width/2)-(r*canvas.width/12)) , ((canvas.height/2)-((r/2)*canvas.height/12)) , r*canvas.width/12 , (r/2)*canvas.height/12);
    c.fill();
    // arc
    c.moveTo(canvas.width/2 , canvas.height/2);
    c.arc( canvas.width/2 , canvas.height/2 , r*canvas.width/12 , Math.PI , Math.PI/2 , true);
    c.fill();
    // triangle
    c.moveTo(canvas.width/2 , canvas.height/2 );
    c.lineTo( ((r/2+6)*canvas.width)/12 , canvas.height/2 );
    c.lineTo( canvas.width/2 , (6+r/2)*canvas.height/12 );
    c.lineTo( canvas.width/2 , canvas.height/2 );
    c.fill();
    c.closePath();
}

function reDraw() {
    c.clearRect(0 , 0 , canvas.width , canvas.height);
    fitToContainer(canvas);
    drawAxes(c);
    fillAxes();
}

function mainSubmit(){
    var y = document.getElementById("MainForm:Yinput").value;
    var x = document.getElementById("MainForm:HiddenX2").value;
    if(!validation(x,y)){
        return;
    }
    var width = canvas.width;
    var height = canvas.height;
    var wDiv = canvas.width / 12;
    var hDiv = canvas.height / 12;
    if(x <= 0) x = (width/2 + x * wDiv);
    else x = (x * wDiv) + width/2;
    if(y >= 0) y = (height / 2 - y * hDiv);
    else y = height/2 + ((-y) * hDiv);
    drawPoint(c , x ,y , inArea(x,y));
}

function drawPoint(context, x, y , inArea){
    context.beginPath();
    if(inArea == 1){
        context.fillStyle = "Green";
    } else {
        context.fillStyle = "Red";
    }
    context.arc(x, y, 3, 0*Math.PI, 2*Math.PI);
    context.fill();
}

function clicCanvas(event) {
    if((R == null) || isNaN(R)){
        alert("Set R");
        return;
    }
    var br = canvas.getBoundingClientRect();
    var x = event.clientX-br.left;
    var y = event.clientY-br.top;
    addPoint(x, y);
    drawPoint(c , x , y , inArea(x,y));
}

function addPoint(x, y) {
    var width = canvas.width;
    var height = canvas.height;
    var wDiv = canvas.width / 12;
    var hDiv = canvas.height / 12;
    if(x <= width/2) x = -(width/2 - x) / wDiv;
    else x = (x - width/2) / wDiv;
    if(y <= height/2) y = (height/2 - y)/ hDiv;
    else y = -(y - height/2) / hDiv;
    document.getElementById("HiddenForm:HiddenX").value=x;
    document.getElementById("HiddenForm:HiddenY").value=y;
    document.getElementById("HiddenForm:HiddenR").value=R;
    document.getElementById("HiddenForm:HiddenSubmit").click();
}

function inArea(x,y){
    var width = canvas.width;
    var height = canvas.height;
    var wDiv = canvas.width / 12;
    var hDiv = canvas.height / 12;
    if(x <= width/2) x = -(width/2 - x) / wDiv;
    else x = (x - width/2) / wDiv;
    if(y <= height/2) y = (height/2 - y)/ hDiv;
    else y = -(y - height/2) / hDiv;
    if (
        ( x<=0 && x>=-R && y>=0 && y<=R/2 ) ||
        ( x<=0 && y<=0 && ((x*x + y*y) <= (R*R))  ) ||
        ( x >= 0 && x <= R/2 && y <= 0 && y >= -R/2 && y>=(x - R/2) )
    ){
        return 1;
    }else{
        return 0;
    }
}

function drawFromTable(radius){
    var oTable = document.getElementById("result_table");
    var rowLength = oTable.rows.length;
    var x, y, r, ent;
    var width = canvas.width;
    var height = canvas.height;
    var wDiv = canvas.width / 12;
    var hDiv = canvas.height / 12;
    var currRad = radius;
    for (i = 1; i<=rowLength; i++){
        var oCells = oTable.rows.item(i).cells;
        x = oCells.item(0).innerHTML;
        y = oCells.item(1).innerHTML;
        r = oCells.item(2).innerHTML;
        ent = oCells.item(3).innerHTML;
        if(x <= 0) x = (width/2 + x * wDiv);
        else x = (x * wDiv) + width/2;

        if(y >= 0) y = (height / 2 - y * hDiv);
        else y = height/2 + ((-y) * hDiv);
        if(currRad == r){

            drawPoint(c, x, y, ent);
        }
    }
}