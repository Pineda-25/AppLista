# AppListas - Buscador de Personajes Dragon Ball

Aplicación Android que consume la API pública de Dragon Ball para buscar personajes por ID y mostrar su información detallada, imagen y transformaciones.

---

## ¿Qué hace la aplicación?

- **Buscar personaje por ID** usando la API `https://dragonball-api.com/api/characters/{id}`
- **Mostrar información** del personaje: Nombre, Ki, Raza y Género
- **Mostrar la imagen** del personaje cargada directamente desde la URL del API
- **Listar transformaciones** del personaje directamente en la pantalla (solo si tiene)
- **Notificar con Toast** si el personaje no fue encontrado y reinicia la UI automáticamente
- **Reiniciar la búsqueda** con un botón que limpia todos los campos y la pantalla

---

## Tecnologías usadas

| Tecnología | Uso |
|---|---|
| Java | Lenguaje principal |
| Volley 1.2.2 | Peticiones HTTP al API |
| Glide 4.16.0 | Carga de imágenes desde URL |
| CardView 1.0.0 | Componentes visuales |
| Dragon Ball API | Fuente de datos pública |

---

## Requisitos previos

### En Windows y Linux necesitas:

- **Android Studio** (versión reciente) → [Descargar aquí](https://developer.android.com/studio)
- **JDK 11 o superior** (Android Studio lo incluye)
- **Git** → [Descargar aquí](https://git-scm.com/)
- Conexión a internet (para descargar dependencias Gradle y consultar el API)

---

## Cómo clonar el proyecto

Abre una terminal (CMD, PowerShell o Bash) y ejecuta:

```bash
git clone https://github.com/Pineda-25/AppLista.git
```

---

## Cómo correrlo en Windows

1. Abre **Android Studio**
2. En la pantalla de inicio haz clic en **"Open"**
3. Navega hasta la carpeta donde clonaste el proyecto y selecciona la carpeta `AppLista`
4. Espera que Android Studio sincronice el proyecto con Gradle (puede tardar unos minutos la primera vez)
5. Conecta un dispositivo Android por USB con **Depuración USB activada**, o crea un emulador desde **Device Manager**
6. Haz clic en el botón **Run** (triángulo verde) o presiona `Shift + F10`

> **Nota:** Si Gradle pide descargar dependencias, acepta y espera que termine.

---

## Cómo correrlo en Linux

### Opción A — Desde Android Studio (interfaz gráfica)

1. Abre Android Studio desde el menú de aplicaciones o ejecuta:
   ```bash
   android-studio &
   ```
2. Haz clic en **"Open"** y selecciona la carpeta del proyecto clonado
3. Espera la sincronización de Gradle
4. Conecta un dispositivo Android o usa un emulador
5. Presiona el botón **Run** o `Shift + F10`

### Opción B — Desde la terminal (sin abrir Android Studio)

1. Entra a la carpeta del proyecto:
   ```bash
   cd AppLista
   ```
2. Dale permisos al script de Gradle:
   ```bash
   chmod +x gradlew
   ```
3. Compila el APK debug:
   ```bash
   export JAVA_HOME=/opt/android-studio/jbr
   export PATH=$JAVA_HOME/bin:$PATH
   ./gradlew assembleDebug
   ```
4. El APK generado estará en:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```
5. Para instalar directamente en un dispositivo conectado:
   ```bash
   ./gradlew installDebug
   ```


## Cómo usar la app

1. Ingresa un **número de ID** en el campo de texto (ejemplo: `1` para Goku, `2` para Vegeta)
2. Presiona **Buscar**
3. Se mostrará la imagen y los datos del personaje
4. Si el personaje tiene transformaciones, aparecerá el botón **"Ver Transformaciones"**
5. Al presionarlo, los nombres de las transformaciones se listan en pantalla
6. Para buscar otro personaje presiona **Reiniciar**

---

## API utilizada

**Dragon Ball API** — [https://dragonball-api.com](https://dragonball-api.com)

Ejemplo de respuesta para el personaje ID 1:

```json
{
  "id": 1,
  "name": "Goku",
  "ki": "60.000.000",
  "race": "Saiyan",
  "gender": "Male",
  "image": "https://dragonball-api.com/characters/goku_normal.webp",
  "transformations": [
    { "name": "Goku SSJ", "ki": "3 Billion" },
    { "name": "Goku SSJ2", "ki": "6 Billion" }
  ]
}
```

---

## Autor

**Pineda-25** — [github.com/Pineda-25](https://github.com/Pineda-25)
