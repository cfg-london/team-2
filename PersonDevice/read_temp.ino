int sensorPin = 0;

float read_temp()
{
   int reading = analogRead(sensorPin);  
 return ((reading - 242) / 2.0 + 22);
}

