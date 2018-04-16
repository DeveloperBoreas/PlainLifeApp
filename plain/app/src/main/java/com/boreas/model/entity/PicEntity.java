package com.boreas.model.entity;

import java.util.List;

/**
 * 作者 boreas
 * 日期 18-3-28
 * 邮箱 13051089921@163.com
 */

public class PicEntity {

    /**
     * data : [{"id":1,"picTitle":"唯美冬季雪山日出景色图","picUrl":"http://img02.tooopen.com/images/20151231/tooopen_sl_153293226424.jpg","picType":"1","picId":"f747efea36a84e3b91a27f661a77b170"},{"id":2,"picTitle":"美丽湖泊景色","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152967971189.jpg","picType":"1","picId":"ccd5cf3746fa4fe2831e1b6931ab4c2c"},{"id":3,"picTitle":"山水瀑布风景图片","picUrl":"http://img02.tooopen.com/images/20160128/tooopen_sl_155637165865.jpg","picType":"1","picId":"85a183d047c34844a621713e22654e0c"},{"id":4,"picTitle":"湖泊中小舟里钓鱼的人","picUrl":"http://img02.tooopen.com/images/20151227/tooopen_sl_152874658287.jpg","picType":"1","picId":"3b02dc834b434afea02d994f1c8eb8ed"},{"id":5,"picTitle":"美丽的山川湖景景色图","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150258544741.jpg","picType":"1","picId":"0cd379614e9c465a8ee920b9eafbbb38"},{"id":6,"picTitle":"美丽的田野风景","picUrl":"http://img02.tooopen.com/images/20151107/tooopen_sl_147958544817.jpg","picType":"1","picId":"102ec67e7678482db668dd8ba2fcdbcd"},{"id":7,"picTitle":"金色的自然风景","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146830826318.jpg","picType":"1","picId":"1ac11cd98eef4fcea39cefb7bcb8e9c7"},{"id":8,"picTitle":"美丽的城市风景","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146836533194.jpg","picType":"1","picId":"5edad925d876464a8bebf7cf0c9efead"},{"id":9,"picTitle":"巴黎圣母院河边侧视图","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146880458243.jpg","picType":"1","picId":"8adacb330783402da19c57cadbe5dbcc"},{"id":10,"picTitle":"头颅形状冰川图片","picUrl":"http://img02.tooopen.com/images/20150918/tooopen_sl_142951516227.jpg","picType":"1","picId":"f9078fab329b43e6bd7ebf223ba7f102"},{"id":11,"picTitle":"绿草的设计图片","picUrl":"http://img02.tooopen.com/images/20151028/tooopen_sl_146680315996.jpg","picType":"1","picId":"c68c1ba2939944138cccac68285b9eaa"},{"id":12,"picTitle":"青草树木高山中的小土路","picUrl":"http://img02.tooopen.com/images/20150509/tooopen_sl_123278585168.jpg","picType":"1","picId":"f9f78955f322441097c29631dba49be1"},{"id":13,"picTitle":"唯美冬季雪山日出景色图","picUrl":"http://img02.tooopen.com/images/20151231/tooopen_sl_153293226424.jpg","picType":"1","picId":"183de26c9865430d82844d6517d6b1e8"},{"id":14,"picTitle":"美丽湖泊景色","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152967971189.jpg","picType":"1","picId":"1f9f928899844c6ba1d0d7428a91a8a4"},{"id":15,"picTitle":"山水瀑布风景图片","picUrl":"http://img02.tooopen.com/images/20160128/tooopen_sl_155637165865.jpg","picType":"1","picId":"6d3f8e35584e4206bce8f2684457e273"},{"id":16,"picTitle":"湖泊中小舟里钓鱼的人","picUrl":"http://img02.tooopen.com/images/20151227/tooopen_sl_152874658287.jpg","picType":"1","picId":"c4163a48960b4360b2cb2fac808c9e4c"},{"id":17,"picTitle":"美丽的山川湖景景色图","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150258544741.jpg","picType":"1","picId":"a775944f92f54c188b181e54c8ea1f71"},{"id":18,"picTitle":"美丽的田野风景","picUrl":"http://img02.tooopen.com/images/20151107/tooopen_sl_147958544817.jpg","picType":"1","picId":"187df395a1e3437cafeaaa7d0fce8291"},{"id":19,"picTitle":"金色的自然风景","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146830826318.jpg","picType":"1","picId":"808b21ba79be4f5a9a38e8be076d9b2d"},{"id":20,"picTitle":"美丽的城市风景","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146836533194.jpg","picType":"1","picId":"bfb8386c495b4cf3be24b9fce30b3011"},{"id":21,"picTitle":"巴黎圣母院河边侧视图","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146880458243.jpg","picType":"1","picId":"6510cf486ad0436391769d7e3d9d7c88"},{"id":22,"picTitle":"头颅形状冰川图片","picUrl":"http://img02.tooopen.com/images/20150918/tooopen_sl_142951516227.jpg","picType":"1","picId":"e008d9a2254d4fc982bc9e6aa096e391"},{"id":23,"picTitle":"绿草的设计图片","picUrl":"http://img02.tooopen.com/images/20151028/tooopen_sl_146680315996.jpg","picType":"1","picId":"5687ded580ac46d4925f9150af88a8f1"},{"id":24,"picTitle":"青草树木高山中的小土路","picUrl":"http://img02.tooopen.com/images/20150509/tooopen_sl_123278585168.jpg","picType":"1","picId":"74d9767ffd854fdaac222dddd6c3489d"},{"id":25,"picTitle":"山清水秀图片素材","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152967333543.jpg","picType":"1","picId":"16a238f0c2ae471b841a5ddc6dda2833"},{"id":26,"picTitle":"浓烈的红枫树林风景","picUrl":"http://img02.tooopen.com/images/20160321/tooopen_sl_156668233795.jpg","picType":"1","picId":"0902063f7454497a9731cd0b62a475fb"},{"id":27,"picTitle":"成都市区日出美景","picUrl":"http://img05.tooopen.com/images/20160131/tooopen_sl_155815434376.jpg","picType":"1","picId":"c0cf47e4fac04d3ba2f2b9e4279c7bc9"},{"id":28,"picTitle":"美丽的冬季雪山景色摄影图","picUrl":"http://img05.tooopen.com/images/20160113/tooopen_sl_154238918466.jpg","picType":"1","picId":"bb1b298db1b145bba89b10c9d405792b"},{"id":29,"picTitle":"美丽的早晨日出","picUrl":"http://img02.tooopen.com/images/20151221/tooopen_sl_152230263385.jpg","picType":"1","picId":"08ca8e5130d3466e98901215c85803f9"},{"id":30,"picTitle":"川流不息 风景自然景观","picUrl":"http://img02.tooopen.com/images/20151220/tooopen_sl_152159783787.jpg","picType":"1","picId":"af100f17fa6546ebadece7c21675ed7b"},{"id":31,"picTitle":"美丽的冬天雪景素材图","picUrl":"http://img02.tooopen.com/images/20151217/tooopen_sl_151831584113.jpg","picType":"1","picId":"36c661e33bd24f3699d8f4000d63d14d"},{"id":32,"picTitle":"山坡风景图片","picUrl":"http://img05.tooopen.com/images/20151101/tooopen_sl_147151496632.jpg","picType":"1","picId":"742a9ead8bf04051996e1ed2bfc53027"},{"id":33,"picTitle":"美丽的海湾景色","picUrl":"http://img02.tooopen.com/images/20151025/tooopen_sl_146344552535.jpg","picType":"1","picId":"e4697aee91d6410b83123ff55e943905"},{"id":34,"picTitle":"清澈明净的湖面倒影","picUrl":"http://img02.tooopen.com/images/20150924/tooopen_sl_143601673955.jpg","picType":"1","picId":"e10d3b1991e54890abe601e07a018408"},{"id":35,"picTitle":"蓝天白云美丽湖水","picUrl":"http://img02.tooopen.com/images/20150922/tooopen_sl_143484126388.jpg","picType":"1","picId":"b316024d6624447eb998182fc3baa07d"},{"id":36,"picTitle":"蜿蜒的山路丘陵景色高清摄影图片","picUrl":"http://img05.tooopen.com/images/20150907/tooopen_sl_141609383591.jpg","picType":"1","picId":"f2e675209edd4f1b9f677c67c2d11fda"},{"id":37,"picTitle":"海上的美丽心形小岛","picUrl":"http://img02.tooopen.com/images/20150912/tooopen_sl_142405562247.jpg","picType":"1","picId":"0db4677b19714405b88a7e2b42a93e53"},{"id":38,"picTitle":"山清水秀图片素材","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152967333543.jpg","picType":"1","picId":"18af1040eaad4a66b77a1679d95d547e"},{"id":39,"picTitle":"浓烈的红枫树林风景","picUrl":"http://img02.tooopen.com/images/20160321/tooopen_sl_156668233795.jpg","picType":"1","picId":"a46a696dfeb4414cb58268ed1233abe3"},{"id":40,"picTitle":"成都市区日出美景","picUrl":"http://img05.tooopen.com/images/20160131/tooopen_sl_155815434376.jpg","picType":"1","picId":"bc2a1f2b984c4fc3bd8fccb68ffa4193"},{"id":41,"picTitle":"美丽的冬季雪山景色摄影图","picUrl":"http://img05.tooopen.com/images/20160113/tooopen_sl_154238918466.jpg","picType":"1","picId":"01cd92b46f17474680d23596861796b9"},{"id":42,"picTitle":"美丽的早晨日出","picUrl":"http://img02.tooopen.com/images/20151221/tooopen_sl_152230263385.jpg","picType":"1","picId":"3278d036da7340a18132fea929353815"},{"id":43,"picTitle":"川流不息 风景自然景观","picUrl":"http://img02.tooopen.com/images/20151220/tooopen_sl_152159783787.jpg","picType":"1","picId":"2c25e13334ea4367bd1b8f8d8382f116"},{"id":44,"picTitle":"美丽的冬天雪景素材图","picUrl":"http://img02.tooopen.com/images/20151217/tooopen_sl_151831584113.jpg","picType":"1","picId":"1f41f0c2fdf94cc4b757522866630790"},{"id":45,"picTitle":"山坡风景图片","picUrl":"http://img05.tooopen.com/images/20151101/tooopen_sl_147151496632.jpg","picType":"1","picId":"8267b7df3e8e401ba4c9830e9d8ca997"},{"id":46,"picTitle":"美丽的海湾景色","picUrl":"http://img02.tooopen.com/images/20151025/tooopen_sl_146344552535.jpg","picType":"1","picId":"60d9c00d2b374a24a1c562623df7d6f7"},{"id":47,"picTitle":"清澈明净的湖面倒影","picUrl":"http://img02.tooopen.com/images/20150924/tooopen_sl_143601673955.jpg","picType":"1","picId":"3976c3f8e0fc46d8af6b4909cef8249c"},{"id":48,"picTitle":"蓝天白云美丽湖水","picUrl":"http://img02.tooopen.com/images/20150922/tooopen_sl_143484126388.jpg","picType":"1","picId":"7f3f8c7cb5254f99ad29d7ac5430b442"},{"id":49,"picTitle":"蜿蜒的山路丘陵景色高清摄影图片","picUrl":"http://img05.tooopen.com/images/20150907/tooopen_sl_141609383591.jpg","picType":"1","picId":"25a2789c8dbb425fa3315a9b54937c66"},{"id":50,"picTitle":"海上的美丽心形小岛","picUrl":"http://img02.tooopen.com/images/20150912/tooopen_sl_142405562247.jpg","picType":"1","picId":"9cb4dd9fb66e47279dafb9447a975247"},{"id":51,"picTitle":"白皑皑的大雪","picUrl":"http://img02.tooopen.com/images/20160328/tooopen_sl_157483442872.jpg","picType":"1","picId":"e754ddaea41a41d288226160d038a466"},{"id":52,"picTitle":"美丽小溪风景","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152965228981.jpg","picType":"1","picId":"b1519a726ceb4271ad22b347292bd443"},{"id":53,"picTitle":"海上青山绿水风景图片","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152902384783.jpg","picType":"1","picId":"443fac14d030454c88953b6e9e27e9b5"},{"id":54,"picTitle":"东京富士山风景图片","picUrl":"http://img02.tooopen.com/images/20160112/tooopen_sl_154182971491.jpg","picType":"1","picId":"87bb785c4b434b60a7a24a0bc92f64dd"},{"id":55,"picTitle":"唯美海洋日出风景","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150249757446.jpg","picType":"1","picId":"f9e5de492ee548c7b9e17838f8a4947f"},{"id":56,"picTitle":"美丽的雪山风景大图","picUrl":"http://img02.tooopen.com/images/20151201/tooopen_sl_150033978675.jpg","picType":"1","picId":"acf196b6aa1e487b8588a2b7ac11f392"},{"id":57,"picTitle":"湖水和青山的照片","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146813831655.jpg","picType":"1","picId":"6bab7db7769b4f69a4589f8505998e97"},{"id":58,"picTitle":"美丽的山水景色","picUrl":"http://img02.tooopen.com/images/20151208/tooopen_sl_150899646542.jpg","picType":"1","picId":"161f20f59bbb4b27a2874862b6e36495"},{"id":59,"picTitle":"寂静山水风景图片","picUrl":"http://img05.tooopen.com/images/20150921/tooopen_sl_143377965417.jpg","picType":"1","picId":"aa29aea5270645f4af1d0ce93a771892"},{"id":60,"picTitle":"站在悬崖边上的人物","picUrl":"http://img02.tooopen.com/images/20151025/tooopen_sl_146341011184.jpg","picType":"1","picId":"9bdf852e960148e985fddd47c339877f"},{"id":61,"picTitle":"湖泽跟群山图片","picUrl":"http://img02.tooopen.com/images/20150922/tooopen_sl_143482329161.jpg","picType":"1","picId":"b1dc47f96707454585b8a8791b121f8d"},{"id":62,"picTitle":"山峰湖泊木船图片","picUrl":"http://img05.tooopen.com/images/20150921/tooopen_sl_143376987757.jpg","picType":"1","picId":"30355e2e63384021b64dc9f4b8e64828"},{"id":63,"picTitle":"绝美山水森林图","picUrl":"http://img02.tooopen.com/images/20150918/tooopen_sl_143047492273.jpg","picType":"1","picId":"58d80ca09edf4e79997875e0275792b6"},{"id":64,"picTitle":"白皑皑的大雪","picUrl":"http://img02.tooopen.com/images/20160328/tooopen_sl_157483442872.jpg","picType":"1","picId":"73452cb855714843879acb49c0561aa9"},{"id":65,"picTitle":"美丽小溪风景","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152965228981.jpg","picType":"1","picId":"49731e7cc8a6496395d902012fb369e8"},{"id":66,"picTitle":"海上青山绿水风景图片","picUrl":"http://img02.tooopen.com/images/20151228/tooopen_sl_152902384783.jpg","picType":"1","picId":"f3987bed41a94d188d054439334a9379"},{"id":67,"picTitle":"东京富士山风景图片","picUrl":"http://img02.tooopen.com/images/20160112/tooopen_sl_154182971491.jpg","picType":"1","picId":"73171a409adc44e1bc2127023ccccd83"},{"id":68,"picTitle":"唯美海洋日出风景","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150249757446.jpg","picType":"1","picId":"a9122833ead04bfe907aa650b0075eb6"},{"id":69,"picTitle":"美丽的雪山风景大图","picUrl":"http://img02.tooopen.com/images/20151201/tooopen_sl_150033978675.jpg","picType":"1","picId":"ee893a40b5814a36bd56cde9abcc2b8f"},{"id":70,"picTitle":"湖水和青山的照片","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146813831655.jpg","picType":"1","picId":"15d8f594ef07495cbf94a25da84b3194"},{"id":71,"picTitle":"美丽的山水景色","picUrl":"http://img02.tooopen.com/images/20151208/tooopen_sl_150899646542.jpg","picType":"1","picId":"183c85e905904f5880a1d9f9ce31b575"},{"id":72,"picTitle":"寂静山水风景图片","picUrl":"http://img05.tooopen.com/images/20150921/tooopen_sl_143377965417.jpg","picType":"1","picId":"11cf89a9ec824975bd979cbb5f79d7d6"},{"id":73,"picTitle":"站在悬崖边上的人物","picUrl":"http://img02.tooopen.com/images/20151025/tooopen_sl_146341011184.jpg","picType":"1","picId":"54539b1aa6884ef9be7baf6c8707cc5e"},{"id":74,"picTitle":"湖泽跟群山图片","picUrl":"http://img02.tooopen.com/images/20150922/tooopen_sl_143482329161.jpg","picType":"1","picId":"1e822df4eb6a46448091617720802ef8"},{"id":75,"picTitle":"山峰湖泊木船图片","picUrl":"http://img05.tooopen.com/images/20150921/tooopen_sl_143376987757.jpg","picType":"1","picId":"7779206b11c94c2ea69f870c79f17c1a"},{"id":76,"picTitle":"绝美山水森林图","picUrl":"http://img02.tooopen.com/images/20150918/tooopen_sl_143047492273.jpg","picType":"1","picId":"0453b8aabf4e42ba8109e01b5f0371d5"},{"id":77,"picTitle":"晚霞中的大山","picUrl":"http://img02.tooopen.com/images/20160323/tooopen_sl_156957263779.jpg","picType":"1","picId":"ba6dedab0658408a8bc45928029687d4"},{"id":78,"picTitle":"瀑布流水风景图","picUrl":"http://img02.tooopen.com/images/20151229/tooopen_sl_153057986957.jpg","picType":"1","picId":"a076dfa94d324bea9066495d2666b8d8"},{"id":79,"picTitle":"雪山下的湖泊全景","picUrl":"http://img02.tooopen.com/images/20151227/tooopen_sl_152879785436.jpg","picType":"1","picId":"3604caed91d04ce498ed6322ce0aad1b"},{"id":80,"picTitle":"唯美冬季雪山景色图片","picUrl":"http://img02.tooopen.com/images/20151231/tooopen_sl_153300559647.jpg","picType":"1","picId":"f666080d5b1d4b8e9522cd2abb8251ef"},{"id":81,"picTitle":"美丽的山川湖景景色高清图片","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150258483761.jpg","picType":"1","picId":"7a686ffafa994ad092f4d114e4c20225"},{"id":82,"picTitle":"湖光山色风景","picUrl":"http://img02.tooopen.com/images/20151104/tooopen_sl_147511469943.jpg","picType":"1","picId":"022003effe6a489ea0a5bbf254e7b79a"},{"id":83,"picTitle":"福州西湖公园图片","picUrl":"http://img02.tooopen.com/images/20151208/tooopen_sl_150914622345.jpg","picType":"1","picId":"4f55608873834fbd993c103b95d5b468"},{"id":84,"picTitle":"美丽的山脉风景高清摄影图片","picUrl":"http://img05.tooopen.com/images/20151007/tooopen_sl_144674118493.jpg","picType":"1","picId":"b368fee0b0a842e481ec3e77e3032850"},{"id":85,"picTitle":"亚洲公园-世外桃源湖寺庙","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146886657229.jpg","picType":"1","picId":"9f12ae326c5d4e518a2ee29223721ad4"},{"id":86,"picTitle":"美丽的湖水跟边上的绿山","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146863346718.jpg","picType":"1","picId":"b7b88446eeda4ccdb7fd3eb44ef823d9"},{"id":87,"picTitle":"树木环保的照片","picUrl":"http://img02.tooopen.com/images/20151028/tooopen_sl_146683912321.jpg","picType":"1","picId":"9f305c293b5b44298e0b89c4f1ce22b8"},{"id":88,"picTitle":"美丽的岩石湖泊美景高清图片","picUrl":"http://img05.tooopen.com/images/20150906/tooopen_sl_141537347411.jpg","picType":"1","picId":"4b6e1d4d937745afb9741f3f5b7f479a"},{"id":89,"picTitle":"晚霞中的大山","picUrl":"http://img02.tooopen.com/images/20160323/tooopen_sl_156957263779.jpg","picType":"1","picId":"ea47a7b085fb43128fb35a684aa1d5cc"},{"id":90,"picTitle":"瀑布流水风景图","picUrl":"http://img02.tooopen.com/images/20151229/tooopen_sl_153057986957.jpg","picType":"1","picId":"b5f40dcad01c4103b53406bc0741d067"},{"id":91,"picTitle":"雪山下的湖泊全景","picUrl":"http://img02.tooopen.com/images/20151227/tooopen_sl_152879785436.jpg","picType":"1","picId":"b44e2083125447f0bee73bb2395dd59d"},{"id":92,"picTitle":"唯美冬季雪山景色图片","picUrl":"http://img02.tooopen.com/images/20151231/tooopen_sl_153300559647.jpg","picType":"1","picId":"61aa34422e9548999dbd0554b28bf6bf"},{"id":93,"picTitle":"美丽的山川湖景景色高清图片","picUrl":"http://img02.tooopen.com/images/20151204/tooopen_sl_150258483761.jpg","picType":"1","picId":"9230198a33ed420497db9c2e4026e6b9"},{"id":94,"picTitle":"湖光山色风景","picUrl":"http://img02.tooopen.com/images/20151104/tooopen_sl_147511469943.jpg","picType":"1","picId":"75a13574c8d6474eb593b8fbd8e9b4f9"},{"id":95,"picTitle":"福州西湖公园图片","picUrl":"http://img02.tooopen.com/images/20151208/tooopen_sl_150914622345.jpg","picType":"1","picId":"d71928c9eaee457db110c00431a11759"},{"id":96,"picTitle":"美丽的山脉风景高清摄影图片","picUrl":"http://img05.tooopen.com/images/20151007/tooopen_sl_144674118493.jpg","picType":"1","picId":"692ee53826884edc9bf979fa969c1445"},{"id":97,"picTitle":"亚洲公园-世外桃源湖寺庙","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146886657229.jpg","picType":"1","picId":"4df421fc02c94037ac60810b0e04e7c0"},{"id":98,"picTitle":"美丽的湖水跟边上的绿山","picUrl":"http://img02.tooopen.com/images/20151030/tooopen_sl_146863346718.jpg","picType":"1","picId":"414bf3fb9fc74465a5e263e50c0c54c9"},{"id":99,"picTitle":"树木环保的照片","picUrl":"http://img02.tooopen.com/images/20151028/tooopen_sl_146683912321.jpg","picType":"1","picId":"3cba412dc84349f0b290f382140087f8"},{"id":100,"picTitle":"美丽的岩石湖泊美景高清图片","picUrl":"http://img05.tooopen.com/images/20150906/tooopen_sl_141537347411.jpg","picType":"1","picId":"7c0f5455b76e4ec8893ab07613583919"}]
     * msg :
     * retCode : 1
     */

    private String msg;
    private int retCode;
    private List<Pic> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public List<Pic> getData() {
        return data;
    }

    public void setData(List<Pic> data) {
        this.data = data;
    }

    public static class Pic {
        /**
         * id : 1
         * picTitle : 唯美冬季雪山日出景色图
         * picUrl : http://img02.tooopen.com/images/20151231/tooopen_sl_153293226424.jpg
         * picType : 1
         * picId : f747efea36a84e3b91a27f661a77b170
         */

        private int id;
        private String picTitle;
        private String picUrl;
        private String picType;
        private String picId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicTitle() {
            return picTitle;
        }

        public void setPicTitle(String picTitle) {
            this.picTitle = picTitle;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getPicType() {
            return picType;
        }

        public void setPicType(String picType) {
            this.picType = picType;
        }

        public String getPicId() {
            return picId;
        }

        public void setPicId(String picId) {
            this.picId = picId;
        }
    }
}
