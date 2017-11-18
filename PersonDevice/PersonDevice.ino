
#include <ESP8266WiFi.h>

const char* ssid     = "Team2";
const char* password = "codeforgoodLaptop";

const char* host = "192.168.137.1";
const char* streamId   = "/addUser.php";
const char* privateKey = "....................";

void  wifi_connect();
int steps = 0;
WiFiClient client;

String inputString;
const int httpPort = 80;

void setup() {
  Serial.begin(9600);
 // Serial.begin(115200);
  delay(10);
  inputString.reserve(200);

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  check_fall_setup();
}

String pers_name = "Ion";
String contactInfo = "0734523122";
#define HIGH_PRIORITY 3
#define MEDIUM_PRIORITY 2
#define LOW_PRIORITY 1


void loop() {

  if (check_fall_loop())
  ;
    //url_transmit_data(pers_name, contactInfo, "fall", HIGH_PRIORITY);
  read_temp();
  //delay(100);
  
}
void url_transmit_data(String pers_name, String contactInfo, String problem, int priority)
{
  Serial.print("connecting to ");
  Serial.println(host);
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  String url = "";
  url += streamId;
  url += "?Name=";
  url += pers_name;
  url += "&Contact=";
  url += contactInfo;
  url += "&Problem=";
  url += problem;
  url += "&Priority=";
  url += priority;

  Serial.print("Requesting URL: ");
  Serial.println(url);
  
  // This will send the request to the server
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: close\r\n\r\n");
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
  
  
  //serialEvent();
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }
  
  Serial.println();
  Serial.println("closing connection");
  delay(100);
  
}


