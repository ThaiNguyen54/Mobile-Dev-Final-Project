@echo off
"C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\Mobile-Dev-Final-Project\\TryYourHair\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=x86" ^
  "-DCMAKE_ANDROID_ARCH_ABI=x86" ^
  "-DANDROID_NDK=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\Mobile-Dev-Final-Project\\TryYourHair\\OpenCV\\build\\intermediates\\cxx\\Debug\\3o6y1h26\\obj\\x86" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\Mobile-Dev-Final-Project\\TryYourHair\\OpenCV\\build\\intermediates\\cxx\\Debug\\3o6y1h26\\obj\\x86" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\Mobile-Dev-Final-Project\\TryYourHair\\OpenCV\\.cxx\\Debug\\3o6y1h26\\x86" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
