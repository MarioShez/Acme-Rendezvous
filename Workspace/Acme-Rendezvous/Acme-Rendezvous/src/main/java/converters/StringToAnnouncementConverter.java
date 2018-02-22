package converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


import repositories.AnnouncementRepository;

import domain.Announcement;

public class StringToAnnouncementConverter implements Converter<String, Announcement>{

	@Autowired
	AnnouncementRepository announcementRepository;

	@Override
	public Announcement convert(final String text) {
		Announcement result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.announcementRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
	
}
