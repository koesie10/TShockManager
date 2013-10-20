TShock Manager for Android
=============

TShock Manager is a TShock server manager for Android that communicates to the server via the REST API.

## Setting up TShock ##

To enable the REST API in TShock and thus make your server ready to use with TShock Manager, you only have to enable one setting in the TShock configuration. Change `RestApiEnabled` to `true`. Make sure you open the port in `RestApiPort` so you can access it from your phone.

Your server is now ready for usage with TShock Manager. Open TShock Manager and add the server. When you click the server you should be able to use all features of the app :).

## Building TShockManager ##
TO DO

## Contributing ##
When you start working on something, create an issue first so that we know that someone is working on it. When you have finished making changes, create a pull request and we will review the changes.

### Adding a new command ###
To add a new command, first add it to `co.tshock.manager.events.EventType` as a new field. Also add the params in a `EventParamInfo` object if needed. The next thing you should do is adding a method in `co.tshock.manager.api.TShockApi`. See the other methods as a reference. The last thing you have to do is add the command to the section in the UI. This can be done in one of the classes of `co.tshock.manager.ui.fragments.dashboard`. See `ServerFragment` for an example.