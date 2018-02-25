package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.RendezvousRepository;
import domain.Rendezvous;

public class StringToRendezvousConverter implements Converter<String, Rendezvous>{
	
	@Autowired
	RendezvousRepository rendezvousRepository;

	@Override
	public Rendezvous convert(final String text) {
		Rendezvous result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.rendezvousRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
