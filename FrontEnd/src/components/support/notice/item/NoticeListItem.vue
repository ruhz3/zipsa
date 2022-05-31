<template>
  <b-tr>
    <b-td>{{ noticeId }}</b-td>
    <b-th class="text-left">
      <router-link
        class="item-link"
        :to="{ name: 'noticeDetail', params: { noticeId: noticeId } }"
        >{{ title }}</router-link
      >
    </b-th>
    <b-td>{{ date | dateFormat }}</b-td>
    <b-td>{{ userId }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    noticeId: String,
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

<style></style>
