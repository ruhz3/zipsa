<template>
  <div class="container-fluid" style="padding: 0px 180px">
    <div class="display-4 underline-illuminating mb-3" style="position: relative; bottom: 140px">
      고객센터
    </div>
    <h1 class="mb-2">글쓰기</h1>
    <hr />
    <qna-input-item type="register" />
  </div>
</template>

<script>
import QnaInputItem from "@/components/support/item/QnaInputItem.vue";

export default {
  name: "QnaWrite",
  components: {
    QnaInputItem,
  },
};
</script>

<style></style>
