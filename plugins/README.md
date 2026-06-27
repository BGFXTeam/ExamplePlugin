- a sample build

just copy these files into `/storage/emulated/0/BGFX/plugins/`
```
ExamplePlugin.dex, ExamplePlugin.json
```

You gotta be loading the plugin by calling this code

```lua
BGFX.loadPlugin("ExamplePlugin")
```