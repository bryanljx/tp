package seedu.foodrem.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.commons.exceptions.DataConversionException;
import seedu.foodrem.commons.util.FileUtil;
import seedu.foodrem.commons.util.JsonUtil;
import seedu.foodrem.model.ReadOnlyFoodRem;

/**
 * A class to access FoodRem data stored as a json file on the hard disk.
 */
public class JsonFoodRemStorage implements FoodRemStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonFoodRemStorage.class);

    private final Path filePath;

    public JsonFoodRemStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFoodRemFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFoodRem> readFoodRem() throws DataConversionException {
        return readFoodRem(filePath);
    }

    /**
     * Similar to {@link #readFoodRem()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFoodRem> readFoodRem(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFoodRem> jsonFoodRem = JsonUtil.readJsonFile(
                filePath, JsonSerializableFoodRem.class);
        if (jsonFoodRem.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFoodRem.get().toModelType());
        } catch (IllegalArgumentException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFoodRem(ReadOnlyFoodRem foodRem) throws IOException {
        saveFoodRem(foodRem, filePath);
    }

    /**
     * Similar to {@link #saveFoodRem(ReadOnlyFoodRem)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFoodRem(ReadOnlyFoodRem foodRem, Path filePath) throws IOException {
        requireNonNull(foodRem);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFoodRem(foodRem), filePath);
    }
}
