$(function () {
   var listUrl="/xiaoyuanshangpu/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999";
    var statusUrl="/xiaoyuanshangpu/shopadmin/modifyproduct";


    getList();
   function getList() {
       $.getJSON(listUrl,function (data) {

       })
       
   }

    
})