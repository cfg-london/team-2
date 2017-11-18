int sensorPin = 0;

int read_temp()
{
   int reading = analogRead(sensorPin);  
 return ((reading - 242) / 2.0 + 22);
}

