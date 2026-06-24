CES3304N Assignment 1 - Refactored Order Management System
==========================================================

LAYOUT
  src/oms/   -> refactored production code (13 files)
  test/oms/  -> JUnit 5 test suite (OrderTest.java)

SMELL -> FIX -> REQUIREMENT MAP
  Global static order fields      -> immutable Order object          F1, F9
  tempDiscount field              -> local variable only             F2, F14
  start/step2/step3 chain         -> single constructor call         F1, F13
  middle()/empty validate()       -> private validate() w/ logic     F12, F13
  Magic numbers (100, 0.1, 5)     -> named constants
  Payment if/else type code       -> PaymentMethod strategy (OCP)    F3, F11
  Phone formatting in process()   -> Customer.getPhoneDigits() (SRP) F4
  Hard-coded "Saved to DB"        -> OrderRepository interface (DIP) F6, F7, F15
  Bicycle inherits startEngine    -> Engine capability iface (LSP)   F8
  TruckHandler class              -> polymorphic Vehicle.move() (OCP) F10

RUN (production)
  javac -d build src/oms/*.java
  java -cp build oms.OMS
  Expected output ends with "Total: 185.0" (behaviour preserved).

RUN TESTS (needs JUnit 5 on classpath - e.g. in IntelliJ/Eclipse, or with the
junit-platform-console-standalone jar):
  javac -d build -cp junit-platform-console-standalone.jar:build src/oms/*.java test/oms/*.java
  java -jar junit-platform-console-standalone.jar -cp build --scan-classpath
