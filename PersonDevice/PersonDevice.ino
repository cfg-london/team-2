
#include <ESP8266WiFi.h>

const char* ssid     = "Team2";
const char* password = "codeforgoodLaptop";

const char* host = "192.168.137.1";
const char* streamId   = "/addUser.php";
const char* privateKey = "....................";

String inputString = "";         // a string to hold incoming data

int velocity=0;
int rpm=0;
int temp_brake=0;
int distance=0;
int humidity=0;
int temp_ambiental=0;
int front_gear=0;
int rear_gear=0;
float x=0, y=0, z=0;
int lights=0;
int temp_body=0;
int brake_condition=0;
unsigned int acceleration=0;
int braking_effort=0;

void  wifi_connect();

void setup() {
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
}



void loop() {
 
url_transmit_data();
delay(5000);
  
}
void url_transmit_data()
{
  // We now create a URI for the request

  /*
   * 
   * 
   * http://localhost/hs/index.php?bodyTemp=25&brakeCondition=100&lights=0&frontGear=1&backGear=1&speed=20&acceleration=5&brakingEffort=0&rpm=0&diskTemp=0&distance=0&humidity=0&extTemp=0&accelX=0&accelY=0&accelZ=0
   */
   Serial.print("connecting to ");
  Serial.println(host);
  
  // Use WiFiClient class to create TCP connections
  
  WiFiClient client;
  
  const int httpPort = 80;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
   
  String url = "";
  
  url += streamId;
  url += "?Name=";
  url += "NameOfPerson";
  url += "&Contact=";
  url += "07456785231";
  url += "&Problem=";
  url += "fall";
  url += "&Priority=";
  url += "9";

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


