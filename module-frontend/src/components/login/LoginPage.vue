<template>
로그인
    <button @click="showPopup">팝업 열기</button>
</template>

<script setup>
import {usePopupStore} from "@/store/popupStore";

const popupStore = usePopupStore();

const showPopup = () => {
    popupStore.openPopup('로그인', '팝업 테스트');
};
</script>

<style scoped>

</style>