<template>
  <b-tr>
    <b-td>{{ articleno }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { articleno: articleno } }">{{
        subject
      }}</router-link>
    </b-th>
    <b-td>{{ regtime | dateFormat }}</b-td>
    <b-td>{{ userid }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    subject: String,
    regtime: String,
    userid: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
