
#include <Wire.h>
#define accel_module (0x53) //Address of ADXL345
#define xyzregister (0x32) //Starting with initial value of x-axis

#define STEP_INTERVAL 400
#define FALL_INTERVAL 2000

#define THRESHOLD_VALUE 50
#define THRESHOLD_FALL_VALUE 600
byte values[6]; //2 bytes for each axis
int xAccel, yAccel, zAccel;
unsigned long lastStep;
unsigned long lastFall;
void check_fall_setup()
{
  Wire.begin();
  Wire.beginTransmission(accel_module);
  Wire.write(0x2D); //POWER_CTL Register
  Wire.write(0);// Wake up
  Wire.endTransmission();
  Wire.beginTransmission(accel_module);
  Wire.write(0x2D);
  Wire.write(16);//Auto Sleep
  Wire.endTransmission();
  Wire.beginTransmission(accel_module);
  Wire.write(0x2D);
  Wire.write(8);//Measure
  Wire.endTransmission();
  Wire.beginTransmission(accel_module);
  Wire.write(0x31); //DATA_FORMAT Register
  Wire.write(0x09); //±4 g range in full resolution (Can be changed)
  Wire.endTransmission();
}


struct Accelerometer
{
  short x;
  short y;
  short z;
}lastAcceleromter, accelerometer;
int check_fall_loop() {
  lastAcceleromter = accelerometer;
  Wire.beginTransmission(accel_module);//Beginning of reading values
  Wire.write(xyzregister);
  Wire.endTransmission();
  Wire.beginTransmission(accel_module);
  Wire.requestFrom(accel_module, 6);
  int i = 0;
  while (Wire.available()) {
    values[i] = Wire.read();
    i++;
  }
  Wire.endTransmission();
  accelerometer.x = ((short)values[1] << 8) | values[0]; // The most significant byte is shifted to the left
  accelerometer.y = ((short)values[3] << 8) | values[2];
  accelerometer.z = ((short)values[5] << 8) | values[4];
  //Serial.print(accelerometer.x);Serial.print(" ");Serial.print(accelerometer.y);Serial.print(" ");Serial.print(accelerometer.z);Serial.print("\n");
  int diff = lastAcceleromter.y - accelerometer.y;
  if (diff < 0)
  {
    diff = diff * (-1);
  }
  if (diff > THRESHOLD_VALUE)
  {
    if ((millis() - lastStep) > STEP_INTERVAL)
    {
      steps++;
      Serial.println("New step");
      lastStep = millis();
    }
  }
  int countDiff = 0;
  if (THRESHOLD_FALL_VALUE < diff)
  {
    countDiff++;
  }

  float x1 = lastAcceleromter.x, x2 = accelerometer.x;
  float y1 = lastAcceleromter.y, y2 = accelerometer.y;

  float val = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  float MIN_VAL = 300;
  Serial.print(0);Serial.print(" ");Serial.print(1500);Serial.print(" ");Serial.println(val);
   if (val > MIN_VAL)
  {
   // PL("I falled");
    if ((millis() - lastFall) > FALL_INTERVAL)
    {
      Serial.println("cazatura");
      return (1);
      lastFall = millis();
    }
  }
  return (0);
}

