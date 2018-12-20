function encodeInp(){
    for(var t=[],e=0;e<36;e++)
        t[e]="0123456789abcdef".substr(Math.floor(16*Math.random()),1);
    return t[14]="4",t[19]="0123456789abcdef".substr(3&t[19]|8,1),t[8]=t[13]=t[18]=t[23]="-",t.join("");
}