package com.lambdaworks.redis;

import java.util.Date;
import java.util.List;

import com.lambdaworks.redis.output.KeyStreamingChannel;
import com.lambdaworks.redis.output.ValueStreamingChannel;

/**
 * Asynchronous executed commands for Keys (Key manipulation/querying).
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 17.05.14 21:10
 */
public interface RedisKeysAsyncConnection<K, V> {
    /**
     * Delete a key.
     * 
     * @param keys the key
     * @return RedisFuture<Long> integer-reply The number of keys that were removed.
     */
    RedisFuture<Long> del(K... keys);

    /**
     * Return a serialized version of the value stored at the specified key.
     * 
     * @param key the key
     * @return RedisFuture<byte[]> bulk-string-reply the serialized value.
     */
    RedisFuture<byte[]> dump(K key);

    /**
     * Determine if a key exists.
     * 
     * @param key the key
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the key exists. `0` if the key does not exist.
     */
    RedisFuture<Boolean> exists(K key);

    /**
     * Set a key's time to live in seconds.
     * 
     * @param key the key
     * @param seconds the seconds type: long
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was set. `0` if `key` does not exist or the timeout could not be set.
     */
    RedisFuture<Boolean> expire(K key, long seconds);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     * 
     * @param key the key
     * @param timestamp the timestamp type: posix time
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was set. `0` if `key` does not exist or the timeout could not be set (see: `EXPIRE`).
     */
    RedisFuture<Boolean> expireat(K key, Date timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp.
     * 
     * @param key the key
     * @param timestamp the timestamp type: posix time
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was set. `0` if `key` does not exist or the timeout could not be set (see: `EXPIRE`).
     */
    RedisFuture<Boolean> expireat(K key, long timestamp);

    /**
     * Find all keys matching the given pattern.
     * 
     * @param pattern the pattern type: patternkey (pattern)
     * @return RedisFuture<List<K>> array-reply list of keys matching `pattern`.
     */
    RedisFuture<List<K>> keys(K pattern);

    /**
     * Find all keys matching the given pattern.
     * 
     * @return RedisFuture<Long> array-reply list of keys matching `pattern`.
     */
    RedisFuture<Long> keys(KeyStreamingChannel<K> channel, K pattern);

    /**
     * Atomically transfer a key from a Redis instance to another one.
     * 
     * @return RedisFuture<String> simple-string-reply The command returns OK on success.
     */
    RedisFuture<String> migrate(String host, int port, K key, int db, long timeout);

    /**
     * Move a key to another database.
     * 
     * @param key the key
     * @param db the db type: long
     * @return RedisFuture<Boolean> integer-reply specifically:
     */
    RedisFuture<Boolean> move(K key, int db);

    /**
     * returns the kind of internal representation used in order to store the value associated with a key.
     * 
     * @param key the key
     * @return RedisFuture<String>
     */
    RedisFuture<String> objectEncoding(K key);

    /**
     * returns the number of seconds since the object stored at the specified key is idle (not requested by read or write
     * operations).
     * 
     * @param key
     * @return
     */
    RedisFuture<Long> objectIdletime(K key);

    /**
     * returns the number of references of the value associated with the specified key.
     * 
     * @param key
     * @return
     */
    RedisFuture<Long> objectRefcount(K key);

    /**
     * Remove the expiration from a key.
     * 
     * @param key the key
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was removed. `0` if `key` does not exist or does not have an associated timeout.
     */
    RedisFuture<Boolean> persist(K key);

    /**
     * Set a key's time to live in milliseconds.
     * 
     * @param key the key
     * @param milliseconds the milliseconds type: long
     */
    RedisFuture<Boolean> pexpire(K key, long milliseconds);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     * 
     * @param key the key
     * @param timestamp the milliseconds-timestamp type: posix time
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was set. `0` if `key` does not exist or the timeout could not be set (see: `EXPIRE`).
     */
    RedisFuture<Boolean> pexpireat(K key, Date timestamp);

    /**
     * Set the expiration for a key as a UNIX timestamp specified in milliseconds.
     * 
     * @param key the key
     * @param timestamp the milliseconds-timestamp type: posix time
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if the timeout was set. `0` if `key` does not exist or the timeout could not be set (see: `EXPIRE`).
     */
    RedisFuture<Boolean> pexpireat(K key, long timestamp);

    /**
     * Get the time to live for a key in milliseconds.
     * 
     * @param key the key
     * @return RedisFuture<Long> integer-reply TTL in milliseconds, or a negative value in order to signal an error (see the
     *         description above).
     */
    RedisFuture<Long> pttl(K key);

    /**
     * Return a random key from the keyspace.
     * 
     * @return RedisFuture<V> bulk-string-reply the random key, or `nil` when the database is empty.
     */
    RedisFuture<V> randomkey();

    /**
     * Rename a key.
     * 
     * @param key the key
     * @param newKey the newkey type: key
     * @return RedisFuture<String> simple-string-reply
     */
    RedisFuture<String> rename(K key, K newKey);

    /**
     * Rename a key, only if the new key does not exist.
     * 
     * @param key the key
     * @param newKey the newkey type: key
     * @return RedisFuture<Boolean> integer-reply specifically:
     * 
     *         `1` if `key` was renamed to `newkey`. `0` if `newkey` already exists.
     */
    RedisFuture<Boolean> renamenx(K key, K newKey);

    /**
     * Create a key using the provided serialized value, previously obtained using DUMP.
     * 
     * @param key the key
     * @param ttl the ttl type: long
     * @param value the serialized-value type: string
     * @return RedisFuture<String> simple-string-reply The command returns OK on success.
     */
    RedisFuture<String> restore(K key, long ttl, byte[] value);

    /**
     * Sort the elements in a list, set or sorted set.
     * 
     * @return RedisFuture<List<V>> array-reply list of sorted elements.
     */
    RedisFuture<List<V>> sort(K key);

    /**
     * Sort the elements in a list, set or sorted set.
     * 
     * @return RedisFuture<Long> array-reply list of sorted elements.
     */
    RedisFuture<Long> sort(ValueStreamingChannel<V> channel, K key);

    /**
     * Sort the elements in a list, set or sorted set.
     * 
     * @return RedisFuture<List<V>> array-reply list of sorted elements.
     */
    RedisFuture<List<V>> sort(K key, SortArgs sortArgs);

    /**
     * Sort the elements in a list, set or sorted set.
     * 
     * @return RedisFuture<Long> array-reply list of sorted elements.
     */
    RedisFuture<Long> sort(ValueStreamingChannel<V> channel, K key, SortArgs sortArgs);

    RedisFuture<Long> sortStore(K key, SortArgs sortArgs, K destination);

    /**
     * Get the time to live for a key.
     * 
     * @param key the key
     * @return RedisFuture<Long> integer-reply TTL in seconds, or a negative value in order to signal an error (see the
     *         description above).
     */
    RedisFuture<Long> ttl(K key);

    /**
     * Determine the type stored at key.
     * 
     * @param key the key
     * @return RedisFuture<String> simple-string-reply type of `key`, or `none` when `key` does not exist.
     */
    RedisFuture<String> type(K key);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<KeyScanCursor<K>> scan();

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<KeyScanCursor<K>> scan(ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<KeyScanCursor<K>> scan(ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<KeyScanCursor<K>> scan(ScanCursor scanCursor);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     * 
     * @param channel
     * @param scanCursor the cursor type: long
     * @param scanArgs
     */
    RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor, ScanArgs scanArgs);

    /**
     * Incrementally iterate the keys space.
     */
    RedisFuture<StreamScanCursor> scan(KeyStreamingChannel<K> channel, ScanCursor scanCursor);

}
