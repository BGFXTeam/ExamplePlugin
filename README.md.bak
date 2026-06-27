# BGFX Example Plugin

A sample Java plugin for the **BGFX Plugin System**.

This project demonstrates how to create a BGFX plugin with:

-   Custom Android UI
-   Floating overlay button
-   Movable menus
-   Script execution through `ScriptManager`
-   Dynamic DEX loading

------------------------------------------------------------------------

## Creating a Plugin

Every plugin must extend:

``` java
com.executor.plugin.Plugin
```

Example:

``` java
public class ExamplePlugin extends Plugin {

    @Override
    public void onLoad(PluginContext ctx) {

        Context context = ctx.getContext();

    }

}
```

The `onLoad()` method is called automatically when BGFX loads the
plugin.

------------------------------------------------------------------------

## Plugin Context

Plugins receive a `PluginContext`.

``` java
@Override
public void onLoad(PluginContext ctx){

    Context context = ctx.getContext();

}
```

The Android context can be used to create:

-   Views
-   Buttons
-   Dialogs
-   Overlay windows
-   Custom menus

------------------------------------------------------------------------

## Building

Build the project:

``` bash
./gradlew assembleRelease
```

After building, extract the generated dex file.

Example:

    app/build/bin/classes.dex

------------------------------------------------------------------------

## Preparing The Plugin

After getting `classes.dex`:

1.  Rename it to your plugin name.

Example:

    ExamplePlugin.dex

2.  Open the dex using:

-   MT Manager
-   NP Manager
-   Any dex editor

3.  Remove:

```{=html}
<!-- -->
```
    com.executor

from the dex.

The final dex should only contain your plugin code.

Example:

    com/example/bgfx/plugin/ExamplePlugin.class

------------------------------------------------------------------------

## Plugin JSON

Create:

    ExamplePlugin.json

Content:

``` json
{
    "name": "ExamplePlugin",
    "main": "com.example.bgfx.plugin.ExamplePlugin"
}
```

The `main` value must match your plugin class.

------------------------------------------------------------------------

## Installing

Place files here:

    /sdcard/BGFX/plugins/

Example:

    /sdcard/BGFX/plugins/

    ├── Example.dex
    └── Example.json

------------------------------------------------------------------------

## Dependencies

This project uses:

    bgfxwrapper.jar

Provided APIs:

``` java
com.executor.plugin.Plugin
com.executor.plugin.PluginContext
com.executor.bgfxui.ScriptManager
com.executor.helpers.Welp
```

------------------------------------------------------------------------

## Notes

-   Class name must match the JSON main path
-   Dex name must match plugin name
-   Remove `com.executor` classes before distributing
-   Plugins can create Android interfaces
-   Plugins can execute BGFX Lua scripts

------------------------------------------------------------------------

## License

Free to modify and create your own BGFX plugins.
