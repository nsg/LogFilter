LogFilter
=========

LogFilter if a simple log filter plugin for Bukkit Minecraft servers.

Install the plugin as usual under plugins/ and start the server. The plugin is disabled by default, set logfilter to true to enable.

You need to edit plugins/LogFilter/config.yml to change the settings, use /reloadlogfilter to reload the configuration (permissions logfilter.reload).

This example hides the string "CookieMonster" from the plugin "Foo".

    logfilter: true
    plugins:
      Foo:
        - CookieMonster

This is a actual example from my server where I hide a few annoying messages from the logs. 
Note the special "Minecraft" rule, this is not a plugin.

    logfilter: true
    plugins:
      LaggMaster:
        - "Server is lagging"
      Minecraft:
        - Runecraft
        - "adding plugin channel"
        - "connecting with mods"
