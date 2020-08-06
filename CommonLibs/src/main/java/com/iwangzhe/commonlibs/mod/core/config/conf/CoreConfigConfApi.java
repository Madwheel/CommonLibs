package com.iwangzhe.commonlibs.mod.core.config.conf;


import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.iwangzhe.commonlibs.base.ConfApi;
import com.iwangzhe.commonlibs.mod.Mgr;
import com.iwangzhe.commonlibs.mod.core.config.CoreConfigMain;
import com.iwangzhe.commonlibs.mod.core.env.CoreEnvMain;

import java.io.InputStream;

public class CoreConfigConfApi extends ConfApi {
    private static CoreConfigConfApi instance = null;

    protected CoreConfigConfApi(CoreConfigMain main) {
        super(main);
    }

    public static CoreConfigConfApi getInstance(CoreConfigMain main) {
        if (instance == null) {
            instance = new CoreConfigConfApi(main);
        }
        return instance;
    }

    private String devData = "{\n" +
            "  \"mod.net.core\": {\n" +
            "    \"apiHost\": \"https://api-dev.pydp888.com\",\n" +
            "    \"cmsHost\": \"https://api-mcms-dev.pydp888.com\",\n" +
            "    \"masterstationHost\": \"https://dev.pydp888.com\",\n" +
            "    \"passportHost\": \"https://passport-dev.pydp888.com\",\n" +
            "    \"bbsHost\": \"https://bbs-dev.pydp888.com\"\n" +
            "  },\n" +
            "  \"mod.biz.system\": {\n" +
            "    \"innerAuthHosts\": [\n" +
            "      \"dev.pydp888.com\",\n" +
            "      \"home-dev.pydp888.com\",\n" +
            "      \"youxue-dev.pydp888.com\",\n" +
            "      \"api-dev.pydp888.com\",\n" +
            "      \"api-mcms-dev.pydp888.com\",\n" +
            "      \"bbs-dev.pydp888.com\",\n" +
            "      \"admin-dev.pydp888.com\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"mod.biz.application\": {\n" +
            "    \"bottomNav\": [\n" +
            "      {\n" +
            "        \"text\": \"首页\",\n" +
            "        \"routUrl\": \"\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"isSelected\": true,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"学习\",\n" +
            "        \"routUrl\": \"https://dev.pydp888.com/kline/1/study/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"擂台\",\n" +
            "        \"routUrl\": \"https://dev.pydp888.com/kline/1/cup/hall\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"奖励\",\n" +
            "        \"routUrl\": \"https://dev.pydp888.com/kline/1/task/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"个人中心\",\n" +
            "        \"routUrl\": \"https://dev.pydp888.com/kline/1/user-center/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      }\n" +
            "      //      {\n" +
            "      //        \"text\": \"开发者\",\n" +
            "      //        \"routUrl\": \"https://dev.pydp888.com/test/develop/\",\n" +
            "      //        \"proport\": 1.0,\n" +
            "      //        \"lineSize\": 1\n" +
            "      //      }\n" +
            "    ],\n" +
            "    \"menu\": {\n" +
            "      \"bbsUrl\": \"https://bbs-dev.pydp888.com/forum.php?mod=forumdisplay&fid=7355\",\n" +
            "      \"roomUrl\": \"/room?roomId=74\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"mod.tool.qq\": {\n" +
            "    \"appName\": \"斗K\",\n" +
            "    \"shareImageStorePath\": \"/stock/shareimg/\",\n" +
            "    \"shareImageStoreName\": \"share.png\",\n" +
            "    \"shareNormalImageUrl\": \"https://imgcdn-prd.pydp888.com/file/0/1/206240/20191008/7fbf789656fd14f99d790514b0d6148a.jpg\"\n" +
            "  },\n" +
            "  \"mod.biz.share\": {\n" +
            "    \"type1\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"room\"\n" +
            "        //        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"copy\"\n" +
            "        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"search\"\n" +
            "        //        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"openBrowser\"\n" +
            "        }\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"fontChange\"\n" +
            "        //        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"feedBack\"\n" +
            "        //        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type2\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"room\"\n" +
            "        //        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"copy\"\n" +
            "        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"search\"\n" +
            "        //        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"openBrowser\"\n" +
            "        }\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"fontChange\"\n" +
            "        //        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"feedBack\"\n" +
            "        //        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type3\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        //        {\n" +
            "        //          \"responseType\": \"native\",\n" +
            "        //          \"itemType\": \"room\"\n" +
            "        //        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"feedBack\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"biz.mod.navbar\": [\n" +
            "    {\n" +
            "      \"page\": \"BaseH5\",\n" +
            "      \"background\": \"#3e2e15\",\n" +
            "      \"widgets\": [\n" +
            "        {\n" +
            "          \"position\": \"left\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"icon\": \"navBack\",\n" +
            "              \"text\": \"返回\",\n" +
            "              \"action\": \"back\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"关闭\",\n" +
            "              \"action\": \"close\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"center\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"right\",\n" +
            "          \"type\": \"icon\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"navShare\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"share\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"BaseNative\",\n" +
            "      \"background\": \"#3e2e15\",\n" +
            "      \"widgets\": [\n" +
            "        {\n" +
            "          \"position\": \"left\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"navBack\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"返回\",\n" +
            "              \"action\": \"back\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"center\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"CommonH5Activity\",\n" +
            "      \"base\": \"BaseH5\",\n" +
            "      \"widgets\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"BBSFragment\",\n" +
            "      \"base\": \"BaseNative\",\n" +
            "      \"widgets\": []\n" +
            "    }\n" +
            "  ],\n" +
            "  \"mod.biz.kline\": {\n" +
            "    \"roomId\": 74,\n" +
            "    \"helpId\": 7355\n" +
            "  },\n" +
            "  \"mod.biz.sharemini\": {\n" +
            "    \"miniprogramType\": 2,\n" +
            "    \"miniName\": \"gh_b8e088f047aa\"\n" +
            "  },\n" +
            "  \"mod.tool.thirdparty\": {\n" +
            "    \"weixin\": \"wxeada13f242d0cccb\"\n" +
            "  }\n" +
            "}";
    private String productData = "{\n" +
            "  \"mod.net.core\": {\n" +
            "    \"apiHost\": \"https://api.iwangzhe.com\",\n" +
            "    \"cmsHost\": \"https://api-mcms.iwangzhe.com\",\n" +
            "    \"masterstationHost\": \"https://iwangzhe.com\",\n" +
            "    \"passportHost\": \"https://passport.iwangzhe.com\",\n" +
            "    \"bbsHost\": \"https://bbs.iwangzhe.com\"\n" +
            "  },\n" +
            "  \"mod.biz.system\": {\n" +
            "    \"innerAuthHosts\": [\n" +
            "      \"iwangzhe.com\",\n" +
            "      \"home.iwangzhe.com\",\n" +
            "      \"youxue.iwangzhe.com\",\n" +
            "      \"api.iwangzhe.com\",\n" +
            "      \"api-mcms.iwangzhe.com\",\n" +
            "      \"bbs.iwangzhe.com\",\n" +
            "      \"book.iwangzhe.com\",\n" +
            "      \"shop.iwangzhe.com\",\n" +
            "      \"help.iwangzhe.com\",\n" +
            "      \"www.iwangzhe.com\",\n" +
            "      \"admin.iwangzhe.com\"\n" +
            "    ]\n" +
            "  },\n" +
            "  \"mod.biz.application\": {\n" +
            "    \"bottomNav\": [\n" +
            "      {\n" +
            "        \"text\": \"首页\",\n" +
            "        \"routUrl\": \"\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"isSelected\": true,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"学习\",\n" +
            "        \"routUrl\": \"https://iwangzhe.com/kline/1/study/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"擂台\",\n" +
            "        \"routUrl\": \"https://iwangzhe.com/kline/1/cup/hall\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"奖励\",\n" +
            "        \"routUrl\": \"https://iwangzhe.com/kline/1/task/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"text\": \"个人中心\",\n" +
            "        \"routUrl\": \"https://iwangzhe.com/kline/1/user-center/index\",\n" +
            "        \"proport\": 1.0,\n" +
            "        \"lineSize\": 1\n" +
            "      }\n" +
            "    ],\n" +
            "    \"menu\": {\n" +
            "      \"bbsUrl\": \"https://bbs.iwangzhe.com/forum.php?mod=forumdisplay&fid=7355\",\n" +
            "      \"roomUrl\": \"/room?roomId=110\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"mod.tool.qq\": {\n" +
            "    \"appName\": \"斗K\",\n" +
            "    \"shareImageStorePath\": \"/stock/shareimg/\",\n" +
            "    \"shareImageStoreName\": \"share.png\",\n" +
            "    \"shareNormalImageUrl\": \"https://imgcdn-prd.pydp888.com/file/0/1/206240/20191008/7fbf789656fd14f99d790514b0d6148a.jpg\"\n" +
            "  },\n" +
            "  \"mod.biz.share\": {\n" +
            "    \"type1\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"copyUrl\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"openBrowser\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type2\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"copyUrl\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"openBrowser\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"type3\": {\n" +
            "      \"items1\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wx\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"pyq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qq\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"qzone\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"wb\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"mail\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"sms\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"items2\": [\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"refresh\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"responseType\": \"native\",\n" +
            "          \"itemType\": \"feedBack\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"biz.mod.navbar\": [\n" +
            "    {\n" +
            "      \"page\": \"BaseH5\",\n" +
            "      \"background\": \"#3e2e15\",\n" +
            "      \"widgets\": [\n" +
            "        {\n" +
            "          \"position\": \"left\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"icon\": \"navBack\",\n" +
            "              \"text\": \"返回\",\n" +
            "              \"action\": \"back\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"关闭\",\n" +
            "              \"action\": \"close\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"center\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"right\",\n" +
            "          \"type\": \"icon\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"navShare\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"share\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"BaseNative\",\n" +
            "      \"background\": \"#3e2e15\",\n" +
            "      \"widgets\": [\n" +
            "        {\n" +
            "          \"position\": \"left\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"click\",\n" +
            "              \"icon\": \"navBack\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"返回\",\n" +
            "              \"action\": \"back\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"position\": \"center\",\n" +
            "          \"type\": \"text\",\n" +
            "          \"items\": [\n" +
            "            {\n" +
            "              \"event\": \"\",\n" +
            "              \"icon\": \"\",\n" +
            "              \"hidden\": \"false\",\n" +
            "              \"text\": \"\",\n" +
            "              \"action\": \"\",\n" +
            "              \"iconUrl\": \"\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"CommonH5Activity\",\n" +
            "      \"base\": \"BaseH5\",\n" +
            "      \"widgets\": []\n" +
            "    },\n" +
            "    {\n" +
            "      \"page\": \"BBSFragment\",\n" +
            "      \"base\": \"BaseNative\",\n" +
            "      \"widgets\": []\n" +
            "    }\n" +
            "  ],\n" +
            "  \"mod.biz.kline\": {\n" +
            "    \"roomId\": 110,\n" +
            "    \"helpId\": 7344\n" +
            "  },\n" +
            "  \"mod.biz.sharemini\": {\n" +
            "    \"miniprogramType\": 0,\n" +
            "    \"miniName\": \"gh_b8e088f047aa\"\n" +
            "  },\n" +
            "  \"mod.tool.thirdparty\": {\n" +
            "    \"weixin\": \"wxe35b5a321f8deb76\"\n" +
            "  }\n" +
            "}";
    protected JSONObject mConfigJson;

    private String readFileStr(InputStream is) {
        String res = "";
        try {
            byte[] buf = new byte[is.available()];
            int n = is.read(buf);
            if (n > 0) {
                res = new String(buf, "UTF8");
            }
            is.close();
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public void born() {
        String configJson;
        AssetManager aM = Mgr.getmContext().getAssets();
        try {
            String config = "config." + CoreEnvMain.getInstance().getEnvName() + ".json";
            d("config", config);
            InputStream is = aM.open(config);
            configJson = readFileStr(is);
            is.close();
        } catch (Exception e) {
            if (CoreEnvMain.getInstance().getEnvName().equals("dev")) {
                configJson = devData;
            } else {
                configJson = productData;
            }
            d(e.toString());
        }
        d("CoreConfig", configJson);
        try {
            mConfigJson = JSON.parseObject(configJson);
        } catch (JSONException e) {
            d("NetCoreConf", "parseFail");
            mConfigJson = null;
        }
    }

    public <T> T getModConf(String key, Class<T> clazz) {
        d("getModConf", key);
        JSONObject item = mConfigJson.getJSONObject(key);
        if (item == null) {
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e) {
                return null;
            } catch (InstantiationException e) {
                return null;
            }
        }
        d("getmodconf", key + ":" + item.toJSONString());
        return JSON.parseObject(item.toJSONString(), clazz);
    }

    public JSONArray getJSONArrayConf(String key) {
        return mConfigJson.getJSONArray(key);
    }

    public JSONObject getJSONObjectConf(String key) {
        return mConfigJson.getJSONObject(key);
    }
}
