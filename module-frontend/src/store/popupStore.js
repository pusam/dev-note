import {defineStore} from "pinia";

export const usePopupStore = defineStore('popup', {
    state: () => ({
        isOpen: false, // 팝업 열림 상태
        title: '',     // 팝업 제목
        msg: '',       // 팝업 메시지
    }),

    actions: {
        openPopup(popupTitle, popupMsg) {
            this.title = popupTitle;   // 제목 설정
            this.msg = popupMsg;       // 메시지 설정
            this.isOpen = true;        // 팝업 열림
        },
        closePopup() {
            this.isOpen = false;       // 팝업 닫힘
        },
    },
});