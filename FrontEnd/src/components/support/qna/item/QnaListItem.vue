<template>
  <b-tr>
    <b-td>{{ qnaId }}</b-td>
    <b-th class="text-left">
      <router-link class="item-link" :to="{ name: 'qnaDetail', params: { qnaId: qnaId } }"
        >{{ title }}<span class="comment-count">&nbsp;&nbsp;[{{ commentCount }}]</span></router-link
      >
    </b-th>
    <b-td>{{ date | dateFormat }}</b-td>
    <b-td>{{ userId }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
  props: {
    qnaId: String,
    commentCount: Number,
    title: String,
    date: String,
    userId: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style scoped>
.comment-count {
  color: darkorange;
}
</style>
