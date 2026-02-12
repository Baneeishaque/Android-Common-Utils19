# Android Common Utils for API 19 (KitKat)
[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/Baneeishaque/Android-Common-Utils19)

## ViewBinding

This project uses ViewBinding instead of the deprecated Kotlin Android Extensions plugin. ViewBinding is enabled in both the `common19` and `tests19` modules.

### Using ViewBinding in Activities

To use ViewBinding in your activities, follow this pattern:

```kotlin
class MyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Access views through binding
        binding.textView.text = "Hello ViewBinding"
    }
}
```

### Migration from Kotlin Android Extensions

The project has been migrated from the deprecated `kotlin-android-extensions` plugin to ViewBinding. If you're updating old code:

- Replace `import kotlinx.android.synthetic.main.*` with ViewBinding
- Replace direct view references (e.g., `textView.text`) with binding references (e.g., `binding.textView.text`)
- Use `findViewById()` for Java code or ViewBinding for Kotlin code

For more information, see: [Migrating the deprecated Kotlin Android Extensions compiler plugin to ViewBinding](https://proandroiddev.com/migrating-the-deprecated-kotlin-android-extensions-compiler-plugin-to-viewbinding-d234c691dec7)
