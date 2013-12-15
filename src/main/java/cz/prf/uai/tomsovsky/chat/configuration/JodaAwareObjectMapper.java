package cz.prf.uai.tomsovsky.chat.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JodaAwareObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;
	
	public JodaAwareObjectMapper() {
		registerModule(new JodaModule());
		configure(com.fasterxml.jackson.databind.SerializationFeature.
			    WRITE_DATES_AS_TIMESTAMPS , false);
	}
}
