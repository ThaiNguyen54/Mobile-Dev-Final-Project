@echo off
"C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\TryYourHair\\OpenCV\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\ndk\\23.1.7779620\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Thai\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\TryYourHair\\OpenCV\\build\\intermediates\\cxx\\Debug\\3o6y1h26\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\TryYourHair\\OpenCV\\build\\intermediates\\cxx\\Debug\\3o6y1h26\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\Study\\Cirriculums\\Mobile Developement\\TT\\Thesis class\\Final Project\\TryYourHair\\OpenCV\\.cxx\\Debug\\3o6y1h26\\arm64-v8a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
