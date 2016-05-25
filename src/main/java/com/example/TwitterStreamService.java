package com.example;

import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterStreamService {

	private Log log = LogFactory.getLog(TwitterStreamService.class);

	private LinkedList<Status> tweets = new LinkedList<>();

	private ConfigurationBuilder cb;

	@Inject
	public TwitterStreamService(@Value("${twitter.oauth.consumerKey}") String consumerKey,
			@Value("${twitter.oauth.consumerSecret}") String consumerSecret,
			@Value("${twitter.oauth.accessToken}") String accessToken,
			@Value("${twitter.oauth.accessTokenSecret}") String accessTokenSecret) {

		this.cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(accessToken);
		cb.setOAuthAccessTokenSecret(accessTokenSecret);

	}

	public Status pollTweet() {
		return tweets.pollFirst();
	}

	@PostConstruct
	public void streamApi() throws InterruptedException {

		TwitterStream twitterStream = new TwitterStreamFactory(this.cb.build()).getInstance();

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatus(Status tweet) {
				tweets.add(tweet);
			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

		};

		
		Float south = 0f;
		Float west = 0f;
		
		Float north = 0f;
		Float east = 0f;

		twitterStream.addListener(listener);
		
		FilterQuery tweetFilterQuery = new FilterQuery();
		tweetFilterQuery.locations(new double[][] { 
				new double[] { west, south,  },
				new double[] { east, north } });
		// See https://dev.twitter.com/docs/streaming-apis/parameters#locations
		// for proper location doc.
		// Note that not all tweets have location metadata set.

		twitterStream.filter(tweetFilterQuery);

	}

}
