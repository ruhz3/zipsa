<template>
  <b-row style="height: 200px; overflow: hidden">
    <b-col cols="1">
      <div
        style="background-color: #f6e45e; width: 100%; height: 100%; border-top-right-radius: 20px"
        v-animate-css="'fadeInLeft'"
      ></div>
    </b-col>
    <b-col cols="11">
      <img
        src="https://picsum.photos/1024/480/?image=302"
        style="width: 100%; border-top-left-radius: 20px"
      />
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "HeaderBanner",
};
</script>
