<template>
  <div>
    <!--지도-->
    <div id="map"></div>
    <!--왼쪽 박스 전체-->
    <div id="search-bar" class="menu-wrap">
      <div class="option">
        <b-row class="ml-2">
          <b-col cols="3" align="left" class="px-1">
            <b-form-select v-model="sidoCode" :options="sidos" @change="gugunList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left" class="px-1">
            <b-form-select v-model="gugunCode" :options="guguns" @change="dongList"></b-form-select>
          </b-col>
          <b-col cols="3" align="left" class="px-1">
            <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
          </b-col>
          <b-col cols="3" align="left">
            <b-button variant="warning" @click="searchApt"><b-icon-search /></b-button>
            <b-button variant="warning" class="ml-1"><b-icon-filter /></b-button>
          </b-col>
        </b-row>
      </div>
    </div>
    <!--검색 결과-->
    <div id="search-result" class="menu-wrap" v-if="searchResultShow">
      <div class="pl-3" style="height: 30px">
        &nbsp;<span style="color: #999a9c">두 번 클릭하면, 해당 지역으로 이동합니다.</span>
        <b-icon-x class="h1" style="float: right" @click="searchResultShow = false"></b-icon-x>
      </div>
      <div style="width: 100%; max-height: 500px; overflow-y: scroll">
        <b-button-group vertical class="w-100 px-3">
          <apart-list-item
            value="house.aptCode"
            v-for="house in houses"
            :key="house.aptCode"
            v-bind="house"
            @return-apt-code="print"
            @click-map-event="zoomSelectApt"
          />
        </b-button-group>
      </div>
    </div>
    <!--상세 정보-->
    <div id="house-detail" class="menu-wrap pl-3" v-if="houseDetailShow">
      <div class="pl-3" style="height: 30px">
        <b-icon-x class="h1" style="float: right" @click="houseDetailShow = false"></b-icon-x>
      </div>
      <div style="width: 100%; max-height: 650px; overflow-y: scroll">
        <h1 class="underline-illuminating">{{ houseDetailInfo.aptName }}</h1>
        <img src="https://picsum.photos/id/1078/500/300" />
        <table class="my-3">
          <tr>
            <th>위치</th>
            <td>
              &nbsp;&nbsp;{{ houseDetailInfo.sidoName }} {{ houseDetailInfo.gugunName }}
              {{ houseDetailInfo.dongName }} {{ houseDetailInfo.jibun }}
            </td>
          </tr>
          <tr>
            <th>준공년도</th>
            <td>&nbsp;&nbsp;{{ houseDetailInfo.buildYear }}년</td>
          </tr>
          <tr>
            <th>최근가격</th>
            <td>&nbsp;&nbsp;{{ houseDetailInfo.recentPrice }}</td>
          </tr>
        </table>
        <div class="mt-5">
          <h3>최근 거래 정보</h3>
          <b-list-group>
            <b-list-group-item v-for="deal in houseDealInfo" :key="deal.no">
              <table>
                <tr>
                  <th>거래일</th>
                  <td>
                    &nbsp;&nbsp; {{ deal.dealYear }}년 {{ deal.dealMonth }}월 {{ deal.dealDay }}일
                  </td>
                </tr>
                <tr>
                  <th>거래금액</th>
                  <td>&nbsp;&nbsp;{{ deal.dealAmount }}</td>
                </tr>
                <tr>
                  <th>전용면적</th>
                  <td>&nbsp;&nbsp; {{ deal.area }}&nbsp;({{ deal.floor }}층)</td>
                </tr>
              </table>
            </b-list-group-item>
          </b-list-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { houseDealList } from "@/api/house";
import { mapState, mapActions, mapMutations } from "vuex";
import ApartListItem from "@/components/search/item/ApartListItem.vue";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  components: {
    ApartListItem,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,

      isSearchClicked: false,
      markers: [],
      infowindow: null,

      searchResultShow: false,
      houseDetailShow: false,
      houseDetailInfo: null,
      houseDealInfo: null,

      isZoomed: false,
      centerLat: null,
      centerLng: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
  },
  created() {
    //SearchBar
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7281fc1e0cce741b1cbe57d33bd72c68";
      document.head.appendChild(script);
    }
  },
  beforeUpdate() {
    this.displayMarkers();
    if (this.isZoomed) {
      console.log(this.centerLat + this.centerLng);
      let coord = new kakao.maps.LatLng(this.centerLat, this.centerLng);
      this.map.setCenter(coord);
      this.map.setLevel(2);
      this.isZoomed = false;
    }
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    // SearchBar
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      this.houseDetailShow = false;
      this.searchResultShow = true;
      if (this.dongCode) this.getHouseList(this.dongCode);
    },

    // Logic
    setIsSearchClicked() {
      this.isSearchClicked = true;
    },
    print(aptCode) {
      console.log(aptCode);
      for (let house of this.houses) {
        if (house.aptCode == aptCode) {
          this.houseDetailInfo = house;
          this.houseDetailShow = true;
          break;
        }
      }
      const params = {
        aptCode: aptCode,
      };
      houseDealList(
        params,
        (response) => {
          this.houseDealInfo = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    zoomSelectApt(lat, lng) {
      this.isZoomed = true;
      this.centerLat = lat;
      this.centerLng = lng;
    },

    // Map
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.50128800000002, 127.03528179999988),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      this.map.setZoomable(false);
    },
    displayMarkers() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      let markerPositions = [];
      for (let house of this.houses) {
        markerPositions.push([house.lat, house.lng]);
      }
      const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));
      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            }),
        );
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds(),
        );
        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 1900px;
  height: 800px;
}
.menu-wrap {
  width: 550px;
  padding: 10px 10px;
  background: white;
  z-index: 1;
  font-size: 18px;
  border-radius: 10px;
}
#search-bar {
  position: absolute;
  top: 150px;
  left: 20px;
}
#search-result {
  position: absolute;
  top: 230px;
  left: 20px;
}
#house-detail {
  position: absolute;
  top: 180px;
  right: 20px;
}
</style>
