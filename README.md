## 目标


### 原理
"# alilite_java" 


```c
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String appId = "999999"; // App ID ，向客服申请

        GatewaySdk sdk = new GatewaySdk(appId);

        GatewaySdk.RequestPayload payload = new GatewaySdk.RequestPayload();
        payload.setTimestamp(1711347382L);

        GatewaySdk.RequestPayload.Content content = new GatewaySdk.RequestPayload.Content();
        content.setExtTradeNo("1711347382");
        content.setRedirectUrl("https://your_successful_webpage.cc"); //回跳地址

        GatewaySdk.RequestPayload.Content.Company company = new GatewaySdk.RequestPayload.Content.Company();
        company.setId("cnogda7i2dkqvfoskpggf");  //收款公司
        company.setName("浙江某某科技有限公司");
        content.setCompany(company);

        GatewaySdk.RequestPayload.Content.Customer customer = new GatewaySdk.RequestPayload.Content.Customer();
        customer.setExtId("user_002");   //付款用户
        customer.setName("张某");  //付款用户
        customer.setAddr("杭州市西湖区"); //付款用户
        customer.setPhone("13958040000"); //付款用户
        customer.setIdCard("331002190000 ");  //根据该身份证号调用支付宝签约及扣款
        content.setCustomer(customer);

        GatewaySdk.RequestPayload.Content.Product product = new GatewaySdk.RequestPayload.Content.Product();
        product.setExtId("product_002");  //产品描述
        product.setName("恰恰香瓜子"); //产品描述
        product.setPrice("1.00");  //价格
        product.setContent("好吃"); //产品描述
        content.setProduct(product);

        GatewaySdk.RequestPayload.Content.Installment installment = new GatewaySdk.RequestPayload.Content.Installment();
        installment.setLimit(1.00);  //付款相关，总价
        installment.setFirst(0.01);  //付款相关， 暂无效
        installment.setNum(2);  //付款相关，平均分2个月， 支付
        installment.setType("SDI"); //付款相关，固定
        content.setInstallment(installment);

        payload.setContent(content);

        try {
            sdk.post("gate/liteContract/create", payload);
            System.out.println("POST request successful.");
        } catch (IOException e) {
            System.err.println("Failed to send POST request: " + e.getMessage());
        }
    }
}

```

### 请求与返回

```c
curl -X POST \
     -H "Content-Type: application/json" \
     -H "X-Gateway-AppId: 999999" \
     -d '{"timestamp":1711347382,"content":{"extTradeNo":"1711347382","redirectUrl":"https://geekland.cc","company":{"id":"cnogda7i2dkqvf","name":"浙江某科技有限公司"},"customer":{"extId":"user_002","name":"张某","addr":"杭州市西湖区","phone":"13958040000","idCard":"3310021981000000"},"product":{"extId":"product_002","name":"恰恰香瓜子","price":"1.00","Content":"好吃"},"installment":{"limit":1.00,"first":0.01,"num":2,"type":"SDI"}}}' \
     https://open.xiadandt.com/gate/liteContract/create


{
  "code": 0,
  "content": {
    "contractId": "co0hdgni2dkrn7or4m8g",
    "customer": {
      "extId": "user_002",
      "name": "张某",
      "addr": "杭州市西湖区",
      "phone": "13958040000",
      "idCard": "33100219810412251X"
    },
    "bill": [
      {
        "index": 1,
        "days": "2024-03-25T08:00:00+08:00",
        "amount": 0.5
      },
      {
        "index": 2,
        "days": "2024-04-25T08:00:00+08:00",
        "amount": 0.5
      }
    ],
    "signUrl": "https://u.alipay.cn/_eLriCTVod5djaQX9hEFxd",
    "status": 12
  },
  "msg": "创建成功"
}

```


申请 APPID
 
<img src="https://raw.githubusercontent.com/284851828/alilite_nodejs/main/github_8888.png" width = 250 height = 250>

联系客服

<img src="https://raw.githubusercontent.com/284851828/alilite_nodejs/main/wx.jpg" width = 300 height = 300>

 

 

