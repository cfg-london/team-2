
#include <ESP8266WiFi.h>

const char* ssid     = "Team2";
const char* password = "codeforgoodLaptop";

const char* host = "192.168.137.1";
const char* privateKey = "....................";
String url;
void  wifi_connect();
int steps = 0;
float temp;
WiFiClient client;

String inputString;
const int httpPort = 80;

void setup() {
  //Serial.begin(9600);
  Serial.begin(115200);
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
#define TEMP_INTERVAL 6000 //60000

#define FALL 0
#define TEMP 1

unsigned long last_milis = 0;

void loop() {

  if (check_fall_loop())
    url_transmit_data(FALL);
  if (millis() - last_milis > TEMP_INTERVAL)
  {
    temp = read_temp();
    url_transmit_data(TEMP);
    last_milis = millis();
  }
    //url_transmit_data(pers_name, contactInfo, "fall", HIGH_PRIORITY);

  //delay(100);
  
}
void url_transmit_data(int type)
{
  Serial.print("connecting to ");
  Serial.println(host);
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  switch(type)
  {
    case FALL:
    url = "addUser.php?Name=" + pers_name + "&Contact=" + contactInfo + "&Problem=" + "FALL" + "&Priority=9&XLoc=0&YLoc=0";
    Serial.println("Fall alert sent");
    break;
    case TEMP:
    Serial.println("Temperature and steps sent");
    url = "addBoardEntry.php?Name=" + pers_name + "&Contact=" + contactInfo + "&Steps=" + steps + "&Temperature=" + temp;
    break;
  }

  Serial.print("Requesting URL: ");
  Serial.println(url);
  
  // This will send the request to the server
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: close\r\n\r\n");
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 1000) { //5000
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }
  
  
  //serialEvent();
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()){
    String line = client.readStringUntil('\r');
    //Serial.print(line);
  }
  
  //Serial.println();
  Serial.println("closing connection");
  //delay(100);
  
}


