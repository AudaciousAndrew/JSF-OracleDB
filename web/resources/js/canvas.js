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
  //  drawFromTable();
}

function reDraw() {
    c.clearRect(0 , 0 , canvas.width , canvas.height);
    fitToContainer(canvas);
    drawAxes(c);
    fillAxes();
}