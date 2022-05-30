<template>
  <div class="container-fluid-xl">
    <div
      style="
        width: 100%;
        height: 900px;
        background-color: #999a9c;
        background-image: url(https://picsum.photos/id/1075/1920/1080);
        padding-top: 100px;
        padding-botton: 100px;
      "
    >
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "MemberView",
};
</script>
