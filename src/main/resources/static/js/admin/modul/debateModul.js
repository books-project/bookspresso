
export function viewDetailInfo(){

}

export function dropDebate(){
    let $checkBoxes = document.querySelectorAll(".check-boxes");

    let dropDebateLists = [];

    for (let i = 0; i < $checkBoxes.length; i++) {
        if ($checkBoxes[i].checked){
            dropDebateLists.push($checkBoxes[i].value);
            console.log("삭제 할 것 " + $checkBoxes[i].value);
        }else{
            console.log("no drop");
        }
    }
    console.log("dropDebateLists = " + dropDebateLists);

    fetch(`/admin/debate/drop/${dropDebateLists}`,{
        method : "POST"
    }).then(resp => {
        if (!resp.ok) {
            throw new Error('응답 오류');
        }
        return resp;
    }).catch(error => {
        console.error("문제 발생 : ", error)
    })

}
