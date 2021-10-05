package springboot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@ContextConfiguration("file:./config/application.yml")
public class YAMLConfig {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	private String environment;
	private List<String> servers = new ArrayList<>();
	private List<String> classNeedCache = new ArrayList<>();
	private String mongo_ip;
	private Integer mongo_port;
	private String mongo_database;
	
	public String getMongo_ip() {
		return mongo_ip;
	}

	public void setMongo_ip(String mongo_ip) {
		this.mongo_ip = mongo_ip;
	}

	public Integer getMongo_port() {
		return mongo_port;
	}

	public void setMongo_port(Integer mongo_port) {
		this.mongo_port = mongo_port;
	}

	public String getMongo_database() {
		return mongo_database;
	}

	public void setMongo_database(String mongo_database) {
		this.mongo_database = mongo_database;
	}

	public List<String> getClassNeedCache() {
		return classNeedCache;
	}

	public void setClassNeedCache(List<String> classNeedCache) {
		this.classNeedCache = classNeedCache;
	}

	private int listen_port;
	// standard getters and setters

	public int getListen_port() {
		return listen_port;
	}

	public void setListen_port(int listen_port) {
		this.listen_port = listen_port;
	}

	int time_to_reload;

	public int getTime_to_reload() {
		return time_to_reload;
	}

	public void setTime_to_reload(int time_to_reload) {
		this.time_to_reload = time_to_reload;
	}

	String card_url;
	String partner_name;
	String key_private_rsa;
	String key_softpin;
	String redis_host;
	int redis_port;
	int redis_db;
	String scan_trs_to_push_queue;
	
	public String getScan_trs_to_push_queue() {
		return scan_trs_to_push_queue;
	}

	public void setScan_trs_to_push_queue(String scan_trs_to_push_queue) {
		this.scan_trs_to_push_queue = scan_trs_to_push_queue;
	}

	public int getRedis_db() {
		return redis_db;
	}

	public void setRedis_db(int redis_db) {
		this.redis_db = redis_db;
	}

	Boolean redis_authen;
	String redis_pass;
	
	public String getRedis_host() {
		return redis_host;
	}

	public void setRedis_host(String redis_host) {
		this.redis_host = redis_host;
	}

	public int getRedis_port() {
		return redis_port;
	}

	public void setRedis_port(int redis_port) {
		this.redis_port = redis_port;
	}

	public Boolean getRedis_authen() {
		return redis_authen;
	}

	public void setRedis_authen(Boolean redis_authen) {
		this.redis_authen = redis_authen;
	}

	public String getRedis_pass() {
		return redis_pass;
	}

	public void setRedis_pass(String redis_pass) {
		this.redis_pass = redis_pass;
	}

	private List<String> whitelist_ip = new ArrayList<>();

	public List<String> getWhitelist_ip() {
		return whitelist_ip;
	}

	public void setWhitelist_ip(List<String> whitelist_ip) {
		this.whitelist_ip = whitelist_ip;
	}

	public String getKey_softpin() {
		return key_softpin;
	}

	public void setKey_softpin(String key_softpin) {
		this.key_softpin = key_softpin;
	}

	public String getCard_url() {
		return card_url;
	}

	public void setCard_url(String card_url) {
		this.card_url = card_url;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public String getKey_private_rsa() {
		return key_private_rsa;
	}

	public void setKey_private_rsa(String key_private_rsa) {
		this.key_private_rsa = key_private_rsa;
	}

	private String context_root;
	public String getContext_root() {
		return context_root;
	}

	public void setContext_root(String context_root) {
		this.context_root = context_root;
	}
}
