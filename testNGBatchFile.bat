set projectLocation=D:\SETUPS\Automation Needs\RoundTripMVN
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause