# BTL_OOP
 
File -> Project Structure -> Modules -> Dependencies -> Cuối hàng Export.... Scope bấm dấu  "+" -> Jars or directory 

-> vào file vừa clone về : BTL_OOP ->  libs -> jars bấm ok là đc

Dùng SDK 8 nhé.

Nếu bị lỗi cannot find lwjgl64 in java.libraly.path:
Edit configurations(bên cạnh nút run main) -> VM option -> copy đoạn sau vào:  -Djava.library.path="libbraby_path"  với library_path là đường dẫn đến file BTL_OOP -> libs -> natives
