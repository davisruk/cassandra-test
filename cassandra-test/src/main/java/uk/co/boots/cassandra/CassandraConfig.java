package uk.co.boots.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import lombok.Getter;

@Getter
@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.contactpoints}")
	private String contactPoints;

	@Value("${cassandra.port}")
	private int port;
	
	@Value("${cassandra.keySpace}")
	private String keySpace;
	
	@Value("${cassandra.basePackages}")
	private String basePackages;

	@Override
	protected String getKeyspaceName() {
		// TODO Auto-generated method stub
		return getKeySpace();
	}

	@Override
	public String[] getEntityBasePackages() {
		// TODO Auto-generated method stub
		return new String[] {getBasePackages()};
	}

	@Override
	public SchemaAction getSchemaAction() {
		// TODO Auto-generated method stub
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
}
