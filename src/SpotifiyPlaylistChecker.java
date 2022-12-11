import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

public class SpotifiyPlaylistChecker
{
    // For all requests an access token is needed
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
//            .setAccessToken("taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk")
            .setClientId("038d89e210794c9889062829c32b994f")
            .setClientSecret("1c5f2916476e406395fe67cd2c03ffdf")
            .build();

    // Create a request object with the optional parameter "market"
    final GetListOfCurrentUsersPlaylistsRequest getSomethingRequest = spotifyApi.getListOfCurrentUsersPlaylists()
            .build();

//    void getSomething_Sync() {
//        try {
//            // Execute the request synchronous
//            final Something something = getSomethingRequest.execute();
//
//            // Print something's name
//            System.out.println("Name: " + something.getName());
//        } catch (Exception e) {
//            System.out.println("Something went wrong!\n" + e.getMessage());
//        }
//    }
//
//    void getSomething_Async() {
//        try {
//            // Execute the request asynchronous
//            final Future<Something> somethingFuture = getSomethingRequest.executeAsync();
//
//            // Do other things...
//
//            // Wait for the request to complete
//            final Something something = somethingFuture.get();
//
//            // Print something's name
//            System.out.println("Name: " + something.getName());
//        } catch (Exception e) {
//            System.out.println("Something went wrong!\n" + e.getMessage());
//        }
//    }
}
