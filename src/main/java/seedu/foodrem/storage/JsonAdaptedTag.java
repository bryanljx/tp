package seedu.foodrem.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.foodrem.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {
    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.getName();
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalArgumentException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalArgumentException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalArgumentException(Tag.EXCEED_MAX_CHARS_MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName);
    }
}
