package common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import common.http.AccessToken;
import common.http.HttpClient;
import common.http.PostParameter;
import common.http.RequestToken;
import common.http.Response;

/**
 * @author Ben
 * 
 */
public class Mobile extends MobileSupport implements java.io.Serializable {

	private static final long serialVersionUID = -3623971676477552916L;

	public static String CONSUMER_KEY = "1552112022";
	public static String CONSUMER_SECRET = "bd9e3b147ece106f9cee159758a28ec1";
	// private String baseURL = Configuration.getScheme() +
	// private String searchBaseURL = Configuration.getScheme() +

//	private String baseURL = Configuration.getScheme()
//			+ "192.168.1.116:8080/rs/";
//			private String searchBaseURL = Configuration.getScheme()
//			+ "192.168.1.116:8080/rs/";
//	private String baseURL = Configuration.getScheme()
//	+ "dev.gzrealmap.com:8099/rs/";
//	private String searchBaseURL = Configuration.getScheme()
//	+ "dev.gzrealmap.com:8099/rs/";
	private String baseURL = Configuration.getScheme()
			+ "rsskbio.xicp.net:7080/rs/";
	private String searchBaseURL = Configuration.getScheme()
			+ "rsskbio.xicp.net:7080/rs/";

	public Mobile() {
		super();
		format.setTimeZone(TimeZone.getTimeZone("GMT"));

		http.setRequestTokenURL(Configuration.getScheme()
				+ "api.t.sina.com.cn/oauth/request_token");
		http.setAuthorizationURL(Configuration.getScheme()
				+ "api.t.sina.com.cn/oauth/authorize");
		http.setAccessTokenURL(Configuration.getScheme()
				+ "api.t.sina.com.cn/oauth/access_token");

	}

	/**
	 * Sets token information
	 * 
	 * @param token
	 * @param tokenSecret
	 */
	public void setToken(String token, String tokenSecret) {
		http.setToken(token, tokenSecret);
	}

	public Mobile(String baseURL) {
		this();
		this.baseURL = baseURL;
	}

	public Mobile(String id, String password) {
		this();
		setUserId(id);
		setPassword(password);
	}

	public Mobile(String id, String password, String baseURL) {
		this();
		setUserId(id);
		setPassword(password);
		this.baseURL = baseURL;
	}

	/**
	 * Sets the base URL
	 * 
	 * @param baseURL
	 *            String the base URL
	 */
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	/**
	 * Returns the base URL
	 * 
	 * @return the base URL
	 */
	public String getBaseURL() {
		return this.baseURL;
	}

	/**
	 * Sets the search base URL
	 * 
	 * @param searchBaseURL
	 *            the search base URL
	 * @since Weibo4J 1.1220
	 */
	public void setSearchBaseURL(String searchBaseURL) {
		this.searchBaseURL = searchBaseURL;
	}

	/**
	 * Returns the search base url
	 * 
	 * @return search base url
	 * @since Weibo4J 1.1220
	 */
	public String getSearchBaseURL() {
		return this.searchBaseURL;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Mobile weibo = (Mobile) o;

		if (!baseURL.equals(weibo.baseURL))
			return false;
		// if (!format.equals(weibo.format)) return false;
		if (!http.equals(weibo.http))
			return false;
		if (!searchBaseURL.equals(weibo.searchBaseURL))
			return false;
		if (!source.equals(weibo.source))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = http.hashCode();
		result = 31 * result + baseURL.hashCode();
		result = 31 * result + searchBaseURL.hashCode();
		result = 31 * result + source.hashCode();
		// result = 31 * result + format.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Mobile{" + "http=" + http + ", baseURL='" + baseURL + '\''
				+ ", searchBaseURL='" + searchBaseURL + '\'' + ", source='"
				+ source + '\'' +
				// ", format=" + format +
				'}';
	}

	public String getResponse(String url, boolean flag) {
		try {
			return get(url, flag).asString();
		} catch (MobileException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param consumerKey
	 *            OAuth consumer key
	 * @param consumerSecret
	 *            OAuth consumer secret
	 * @since Weibo4J 1.1220
	 */
	public synchronized void setOAuthConsumer(String consumerKey,
			String consumerSecret) {
		this.http.setOAuthConsumer(consumerKey, consumerSecret);
	}

	/**
	 * Retrieves a request token
	 * 
	 * @return generated request token.
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 * @since Weibo4J 1.1220
	 * @see <a href="http://oauth.net/core/1.0/#auth_step1">OAuth Core 1.0 -
	 *      6.1. Obtaining an Unauthorized Request Token</a>
	 */
	public RequestToken getOAuthRequestToken() throws MobileException {
		return http.getOAuthRequestToken();
	}

	public RequestToken getOAuthRequestToken(String callback_url)
			throws MobileException {
		return http.getOauthRequestToken(callback_url);
	}

	/**
	 * Retrieves an access token assosiated with the supplied request token.
	 * 
	 * @param requestToken
	 *            the request token
	 * @return access token associsted with the supplied request token.
	 * @throws MobileException
	 *             when Weibo service or network is unavailable, or the user has
	 *             not authorized
	 * @see <a
	 *      href="http://open.t.sina.com.cn/wiki/index.php/Oauth/access_token">Oauth/access
	 *      token </a>
	 * @see <a href="http://oauth.net/core/1.0/#auth_step2">OAuth Core 1.0 -
	 *      6.2. Obtaining User Authorization</a>
	 * @since Weibo4J 1.1220
	 */
	public synchronized AccessToken getOAuthAccessToken(
			RequestToken requestToken) throws MobileException {
		return http.getOAuthAccessToken(requestToken);
	}

	/**
	 * Retrieves an access token assosiated with the supplied request token and
	 * sets userId.
	 * 
	 * @param requestToken
	 *            the request token
	 * @param pin
	 *            pin
	 * @return access token associsted with the supplied request token.
	 * @throws MobileException
	 *             when Weibo service or network is unavailable, or the user has
	 *             not authorized
	 * @see <a
	 *      href="http://open.t.sina.com.cn/wiki/index.php/Oauth/access_token">Oauth/access
	 *      token </a>
	 * @see <a href="http://oauth.net/core/1.0/#auth_step2">OAuth Core 1.0 -
	 *      6.2. Obtaining User Authorization</a>
	 * @since Weibo4J 1.1220
	 */
	public synchronized AccessToken getOAuthAccessToken(
			RequestToken requestToken, String pin) throws MobileException {
		AccessToken accessToken = http.getOAuthAccessToken(requestToken, pin);
		setUserId(accessToken.getScreenName());
		return accessToken;
	}

	/**
	 * Retrieves an access token assosiated with the supplied request token and
	 * sets userId.
	 * 
	 * @param token
	 *            request token
	 * @param tokenSecret
	 *            request token secret
	 * @return access token associsted with the supplied request token.
	 * @throws MobileException
	 *             when Weibo service or network is unavailable, or the user has
	 *             not authorized
	 * @see <a
	 *      href="http://open.t.sina.com.cn/wiki/index.php/Oauth/access_token">Oauth/access
	 *      token </a>
	 * @see <a href="http://oauth.net/core/1.0/#auth_step2">OAuth Core 1.0 -
	 *      6.2. Obtaining User Authorization</a>
	 * @since Weibo4J 1.1220
	 */
	public synchronized AccessToken getOAuthAccessToken(String token,
			String tokenSecret) throws MobileException {
		AccessToken accessToken = http.getOAuthAccessToken(token, tokenSecret);
		setUserId(accessToken.getScreenName());
		return accessToken;
	}

	/**
	 * Retrieves an access token assosiated with the supplied request token.
	 * 
	 * @param token
	 *            request token
	 * @param tokenSecret
	 *            request token secret
	 * @param oauth_verifier
	 *            oauth_verifier or pin
	 * @return access token associsted with the supplied request token.
	 * @throws MobileException
	 *             when Weibo service or network is unavailable, or the user has
	 *             not authorized
	 * @see <a
	 *      href="http://open.t.sina.com.cn/wiki/index.php/Oauth/access_token">Oauth/access
	 *      token </a>
	 * @see <a href="http://oauth.net/core/1.0/#auth_step2">OAuth Core 1.0 -
	 *      6.2. Obtaining User Authorization</a>
	 * @since Weibo4J 1.1220
	 */
	public synchronized AccessToken getOAuthAccessToken(String token,
			String tokenSecret, String oauth_verifier) throws MobileException {
		return http.getOAuthAccessToken(token, tokenSecret, oauth_verifier);
	}

	public synchronized AccessToken getXAuthAccessToken(String userId,
			String passWord, String mode) throws MobileException {
		return http.getXAuthAccessToken(userId, passWord, mode);
	}

	/**
	 * Sets the access token
	 * 
	 * @param accessToken
	 *            accessToken
	 * @since Weibo4J 1.1220
	 */
	public void setOAuthAccessToken(AccessToken accessToken) {
		this.http.setOAuthAccessToken(accessToken);
	}

	/**
	 * Sets the access token
	 * 
	 * @param token
	 *            token
	 * @param tokenSecret
	 *            token secret
	 * @since Weibo4J 1.1220
	 */
	public void setOAuthAccessToken(String token, String tokenSecret) {
		setOAuthAccessToken(new AccessToken(token, tokenSecret));
	}

	/**
	 * Issues an HTTP GET request.
	 * 
	 * @param url
	 *            the request url
	 * @param authenticate
	 *            if true, the request will be sent with BASIC authentication
	 *            header
	 * @return the response
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 */

	private Response get(String url, boolean authenticate)
			throws MobileException {
		return get(url, null, authenticate);
	}

	/**
	 * Issues an HTTP GET request.
	 * 
	 * @param url
	 *            the request url
	 * @param authenticate
	 *            if true, the request will be sent with BASIC authentication
	 *            header
	 * @param name1
	 *            the name of the first parameter
	 * @param value1
	 *            the value of the first parameter
	 * @return the response
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 */

	protected Response get(String url, String name1, String value1,
			boolean authenticate) throws MobileException {
		return get(url,
				new PostParameter[] { new PostParameter(name1, value1) },
				authenticate);
	}

	/**
	 * Issues an HTTP GET request.
	 * 
	 * @param url
	 *            the request url
	 * @param name1
	 *            the name of the first parameter
	 * @param value1
	 *            the value of the first parameter
	 * @param name2
	 *            the name of the second parameter
	 * @param value2
	 *            the value of the second parameter
	 * @param authenticate
	 *            if true, the request will be sent with BASIC authentication
	 *            header
	 * @return the response
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 */

	protected Response get(String url, String name1, String value1,
			String name2, String value2, boolean authenticate)
			throws MobileException {
		return get(url, new PostParameter[] { new PostParameter(name1, value1),
				new PostParameter(name2, value2) }, authenticate);
	}

	/**
	 * Issues an HTTP GET request.
	 * 
	 * @param url
	 *            the request url
	 * @param params
	 *            the request parameters
	 * @param authenticate
	 *            if true, the request will be sent with BASIC authentication
	 *            header
	 * @return the response
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 */
	protected Response get(String url, PostParameter[] params,
			boolean authenticate) throws MobileException {
		// if (url.indexOf("?") == -1) {
		// url += "?source=" + CONSUMER_KEY;
		// } else if (url.indexOf("source") == -1) {
		// url += "&source=" + CONSUMER_KEY;
		// }
		if (null != params && params.length > 0) {
			url += "?" + HttpClient.encodeParameters(params);
		}
		return http.get(url, authenticate);
	}

	protected Response post(String url, PostParameter[] params,
			boolean authenticate) throws MobileException {
		return http.post(url, params, authenticate);
	}

	/**
	 * Issues an HTTP GET request.
	 * 
	 * @param url
	 *            the request url
	 * @param params
	 *            the request parameters
	 * @param paging
	 *            controls pagination
	 * @param authenticate
	 *            if true, the request will be sent with BASIC authentication
	 *            header
	 * @return the response
	 * @throws MobileException
	 *             when Weibo service or network is unavailable
	 */
	protected Response get(String url, PostParameter[] params, Paging paging,
			boolean authenticate) throws MobileException {
		if (null != paging) {
			List<PostParameter> pagingParams = new ArrayList<PostParameter>(4);
			if (-1 != paging.getMaxId()) {
				pagingParams.add(new PostParameter("max_id", String
						.valueOf(paging.getMaxId())));
			}
			if (-1 != paging.getSinceId()) {
				pagingParams.add(new PostParameter("since_id", String
						.valueOf(paging.getSinceId())));
			}
			if (-1 != paging.getPage()) {
				pagingParams.add(new PostParameter("page", String
						.valueOf(paging.getPage())));
			}
			if (-1 != paging.getCount()) {
				if (-1 != url.indexOf("search")) {
					// search api takes "rpp"
					pagingParams.add(new PostParameter("rpp", String
							.valueOf(paging.getCount())));
				} else {
					pagingParams.add(new PostParameter("count", String
							.valueOf(paging.getCount())));
				}
			}
			PostParameter[] newparams = null;
			PostParameter[] arrayPagingParams = pagingParams
					.toArray(new PostParameter[pagingParams.size()]);
			if (null != params) {
				newparams = new PostParameter[params.length
						+ pagingParams.size()];
				System.arraycopy(params, 0, newparams, 0, params.length);
				System.arraycopy(arrayPagingParams, 0, newparams,
						params.length, pagingParams.size());
			} else {
				if (0 != arrayPagingParams.length) {
					String encodedParams = HttpClient
							.encodeParameters(arrayPagingParams);
					if (-1 != url.indexOf("?")) {
						url += "&source=" + CONSUMER_KEY + "&" + encodedParams;
					} else {
						url += "?source=" + CONSUMER_KEY + "&" + encodedParams;
					}
				}
			}
			return get(url, newparams, authenticate);
		} else {
			return get(url, params, authenticate);
		}
	}

	private SimpleDateFormat format = new SimpleDateFormat(
			"EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
}
